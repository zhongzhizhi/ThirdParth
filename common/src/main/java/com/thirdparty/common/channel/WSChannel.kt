package com.thirdparty.common.channel

import com.dosmono.logger.Logger
import com.thirdparty.common.config.CommonConstants
import com.thirdparty.common.config.Error
import com.thirdparty.common.gson.GsonFactory
import com.thirdparty.common.thread.ExecutorManager
import com.thirdparty.common.util.TypeUtils
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger

internal class WSChannel(private val wsUrl: String, val iCallback: ICallback?) {

    companion object {
        private const val STATE_DISCONNECTED = 2
        private const val STATE_CONNECTING = 1
        private const val STATE_CONNECTED = 0

        private const val USER_CANCEL = 10086

        private var mChannel: WSChannel? = null

        fun addCallback(callback: ICallback){
            mChannel = WSChannel(CommonConstants.WS_URL,callback).apply { connect() }
        }
    }

    interface ICallback {
        fun onConnected(channel: WSChannel?)
        fun onError(code: Int)
        fun onResponse(reply: String)
    }

    private val mState = AtomicInteger(STATE_DISCONNECTED)

    private var mClient: WebSocketClient? = null

    private var isCompleted = false

    private fun connect() {
        isCompleted = false
        wsconnect()
    }

    fun <T> sendMessage(body: T,session: Int): Int {
        val packet = Packet<T>()
        packet.setCmd(CommonConstants.WEBSOCKET_CMD.toByte())
        packet.setFlags(16)
        packet.setBody(body)
        packet.setSessionId(session)

        return sendMessage(GsonFactory.getGson().toJson(packet))
    }

    fun cancel() {
        isCompleted = true
        mClient?.close(USER_CANCEL, "user cancel")
    }

    private fun sendMessage(message: String): Int {
        if(mClient?.isOpen == true) {
            try {
                if(mClient?.isOpen == true) {
                    mClient?.send(message)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }else {
            val reconnect = mClient?.reconnectBlocking()
            Logger.w("websocket, did not open, reconnect $reconnect")
            if(mClient?.isOpen == true) {
                mClient?.send(message)
            }
        }

        return Error.ERR_NONE
    }

    private fun handlerReply(response: ReplyPacket<*>) {
        when(response.code) {
            Error.ERR_NONE, Error.HTTP_SUCCESS -> {
//                iCallback?.onResponse(response)
            }
            else -> {
                iCallback?.onError(response.code)
            }
        }
    }

    private fun wsconnect() {
        getThread().execute {
            Logger.i("start ws connect, $mState")
            if(mState.compareAndSet(STATE_DISCONNECTED, STATE_CONNECTING)) {
                try {
                    val uri = URI(wsUrl)
                    mClient = MyWebsocketClient(uri)
                    mClient?.connect()
                }catch (e: Exception) {
                    e.printStackTrace()
                    mState.set(STATE_DISCONNECTED)
                }
            }

            waitConnected(3000)
        }
    }

    private fun waitConnected(timeMS: Long) {
        val delay = 10L
        var count = timeMS / delay
        while (true) {
            if(mState.get() == STATE_CONNECTING) {
                if(count-- <= 0) break
                else sleep(delay)
            }else {
                break
            }
        }
    }

    private fun sleep(timeMS: Long) {
        try {
            Thread.sleep(timeMS)
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private inner class MyWebsocketClient(url: URI) : WebSocketClient(url) {

        override fun onOpen(handshakedata: ServerHandshake?) {
            mState.set(STATE_CONNECTED)
            Logger.i("onOpen ${handshakedata?.httpStatus}, ${handshakedata?.httpStatusMessage}")
            iCallback?.onConnected(mChannel)
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            mState.set(STATE_DISCONNECTED)
            Logger.i("onClose $code, $reason, $remote")
            if(!isCompleted) {
                iCallback?.onError(code)
            }
        }

        override fun onMessage(message: String?) {
//            Logger.i("onMessage : $message")
            if(message != null) {
                try {
                    val type = TypeUtils.genericType<Packet<ReplyContent<String>>>()
                    val reply = GsonFactory.getGson().fromJson<Packet<ReplyContent<String>>>(message, type)
                    val content = reply.body.content
                    val response = GsonFactory.getGson().fromJson(content, ReplyPacket::class.java)

                    when(reply.cmd.toInt()) {
                        CommonConstants.WEBSOCKET_CMD -> {
//                            Logger.i("response audio : $response")
                            handlerReply(response)
                        }
                    }
//                    Logger.i("reply : $reply")
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        override fun onError(ex: java.lang.Exception?) {
            if(!isCompleted) {
                Logger.e(ex, "onError : $ex")
                iCallback?.onError(Error.ERR_NETWORK_EXCEPTION)
            }
        }
    }

    private fun getThread(): ThreadPoolExecutor {
        return ExecutorManager.build().getCommonThread(3)
    }
}