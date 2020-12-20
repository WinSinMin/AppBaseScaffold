package com.wsm.base_scaffold.data.response

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 13:43
 * @Description:
 */
class DataResult<T> constructor(t: T, responseStatus: ResponseStatus) {
    var result: T
    var responseStatus: ResponseStatus

    init {
        this.result = t
        this.responseStatus = responseStatus
    }

    interface Result<T> {
        fun onResult(dataResult: DataResult<T>)
    }
}

