package com.wsm.base_scaffold.domain.usecase

import android.os.Handler
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 11:18
 * @Description:
 */
class UseCaseThreadPoolScheduler : UseCaseScheduler {
    companion object {
        val POOL_SIZE = 2
        val MAX_POOL_SIZE = 4 * 2
        val FIXED_POOL_SIZE = 4
        val TIMEOUT = 30
    }

    var threadPoolExecutor: ThreadPoolExecutor? = null
    private val handler = Handler()

    open fun UseCaseThreadPoolScheduler() {
        threadPoolExecutor = ThreadPoolExecutor(FIXED_POOL_SIZE, FIXED_POOL_SIZE, TIMEOUT.toLong(), TimeUnit.SECONDS, LinkedBlockingDeque())
    }


    override fun execute(runnable: Runnable?) {
        threadPoolExecutor?.execute(runnable)
    }

    override fun <V : IResponseValues> notifyResponse(response: V?, callback: IUseCaseCallback<V>?) {
        handler.post { callback?.onSuccess(response) }
    }

    override fun <V : IResponseValues> onError(callback: IUseCaseCallback<V>?) {
        handler.post { callback?.onError() }
    }
}