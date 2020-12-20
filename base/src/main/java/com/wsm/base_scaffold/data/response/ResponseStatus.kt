package com.wsm.base_scaffold.data.response

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 13:46
 * @Description:
 */

class  ResponseStatus @JvmOverloads constructor(responseCode: String = "", success: Boolean = true, source: Enum<ResultSource> = ResultSource.NETWORK) {
     var responseCode: String = responseCode
     var success: Boolean = success
     var source: Enum<ResultSource> = source
}