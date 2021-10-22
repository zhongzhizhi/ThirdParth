package com.thirdparty.common.thread

import com.dosmono.logger.Logger
import java.util.concurrent.*

/**
 * Created by Unpar on 17/8/3.
 */
class ExecutorManager private constructor(){
    private val THREAD_NAME_PREFIX = "framework"
    private val TIMER_THREAD_NAME = "$THREAD_NAME_PREFIX-timer-"
    private val COMMON_THREAD_NAME = "$THREAD_NAME_PREFIX-common-"

    private var timerThread: ScheduledExecutorService? = null
    private var commonThread: ThreadPoolExecutor? = null

    init {
        Logger.d("executor manager init")
    }

    fun getCommonThread() : ThreadPoolExecutor {
        return getCommonThread(3)
    }

    fun getCommonThread(threadCount: Int) : ThreadPoolExecutor {
        if(commonThread == null || commonThread!!.isShutdown) {
            commonThread = ThreadPoolExecutor(1, threadCount,
                    0L, TimeUnit.MICROSECONDS,
                    LinkedBlockingDeque<Runnable>(100),
                    NamedThreadFactory(COMMON_THREAD_NAME),
                    RecordRejectedHandler(COMMON_THREAD_NAME))
        }
        return commonThread!!
    }

    fun getTimerThread() : ScheduledExecutorService {
        if(timerThread == null || timerThread!!.isShutdown) {
            timerThread = ScheduledThreadPoolExecutor(1,
                    NamedThreadFactory(TIMER_THREAD_NAME),
                    RecordRejectedHandler(TIMER_THREAD_NAME))
        }
        return timerThread!!
    }

    private fun shutdown() {
        shutdownTimerThread()
        shutdownCommonThread()
    }


    fun shutdownTimerThread() {
        timerThread?.shutdownNow()
        timerThread = null
    }

    fun shutdownCommonThread() {
        commonThread?.shutdownNow()
        commonThread = null
    }

    companion object {
        private var instance: ExecutorManager? = null

        fun build() : ExecutorManager {
            if(instance == null) {
                synchronized(ExecutorManager::class) {
                    if(instance == null) {
                        instance = ExecutorManager()
                    }
                }
            }
            return instance!!
        }

        fun destroy() {
            instance?.shutdown()
            instance = null
        }
    }

    class RecordRejectedHandler constructor(name: String) : RejectedExecutionHandler {
        private val name = name

        override fun rejectedExecution(r: Runnable?, pool: ThreadPoolExecutor?) {
            //To change body of created functions use File | Settings | File Templates.
            Logger.w("task $name was rejected, runnable = $r")
        }
    }
}