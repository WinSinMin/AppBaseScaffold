package com.wsm.base_scaffold.domain.usecase

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 11:09
 * @Description:
 */
abstract class UseCase<Q : IRequestValues, P : IResponseValues> {
    var requestValues: Q? = null
    var useCaseCallback: IUseCaseCallback<P>? = null
    open fun run() = requestValues?.let { executeUseCase(it) }
    protected abstract fun executeUseCase(requestValue: Q)

}

interface IRequestValues {

}

interface IResponseValues {

}

interface IUseCaseCallback<R> {
    fun onSuccess(response: R?)
    fun onError() {}
}