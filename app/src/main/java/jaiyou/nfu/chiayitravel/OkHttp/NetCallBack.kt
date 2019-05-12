package jaiyou.nfu.chiayitravel.OkHttp

import okhttp3.Call
import okhttp3.Response
import java.io.IOException

interface NetCallBack {
    //链接成功执行的方法
    fun onFailure(code: Int)

    //链接失败执行的方法
    fun onResponse(json: String)
}