package com.wsm.base_scaffold.domain.usecase

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 11:19
 * @Description:
 */
interface UseCaseScheduler {
    fun execute(runnable: Runnable?)

    fun <V : IResponseValues> notifyResponse(response: V?, callback: IUseCaseCallback<V>?)

    fun <V : IResponseValues> onError(callback: IUseCaseCallback<V>?)

}