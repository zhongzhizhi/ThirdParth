package com.thirdparty.common.thread

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Unpar on 17/8/3.
 */
class NamedThreadFactory : ThreadFactory {

    private val threadNumber = AtomicInteger(1)
    private var namePrefix: String
    private val group: ThreadGroup

    constructor(namePrefix: String) {
        this.namePrefix = namePrefix
        group = Thread.currentThread().threadGroup
    }

    fun newThread(name: String, runnable: Runnable?) : Thread {
        return Thread(runnable, name)
    }

    override fun newThread(runnable: Runnable?): Thread {
        //To change body of created functions use File | Settings | File Templates.
        val thread = newThread(namePrefix + threadNumber.getAndIncrement(), runnable)
        if(thread.isDaemon) thread.isDaemon = false
        return thread
    }

}