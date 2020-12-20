package com.wsm.base_scaffold.domain.usecase

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 11:37
 * @Description:
 */
class UseCaseHandler private constructor() {
    companion object {
        val instance = Holder.create
    }

    private object Holder {
        val create = UseCaseHandler()
    }

    private var useCaseScheduler: UseCaseScheduler? = null


    fun <T : IRequestValues, R : IResponseValues> execute(useCase: UseCase<T, R>, value: T, callback: IUseCaseCallback<R>) {
        useCase.requestValues = value
        useCase.useCaseCallback = UiCallBackWrapper(callback, this)
        useCaseScheduler?.execute(useCase::run)
    }


    private fun <V : IResponseValues> notifyResponse(response: V?, callback: IUseCaseCallback<V>?) {
        useCaseScheduler?.notifyResponse(response, callback)
    }


    private fun <V : IResponseValues> nofityError(callback: IUseCaseCallback<V>?) {
        useCaseScheduler?.onError(callback)
    }

    open class UiCallBackWrapper<V : IResponseValues>(callback: IUseCaseCallback<V>?, handler: UseCaseHandler?) : IUseCaseCallback<V> {
        private val callback: IUseCaseCallback<V>?
        private val useCaseHandler: UseCaseHandler?

        init {
            this.callback = callback
            this.useCaseHandler = handler
        }

        override fun onSuccess(response: V?) {
            useCaseHandler?.notifyResponse(response, callback)
        }

        override fun onError() {
            super.onError()
            useCaseHandler?.nofityError(callback)
        }

    }

}

