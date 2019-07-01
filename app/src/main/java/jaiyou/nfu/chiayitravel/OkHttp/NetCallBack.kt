package jaiyou.nfu.chiayitravel.OkHttp

import okhttp3.Call
import okhttp3.Response
import java.io.IOException

interface NetCallBack {
    //連接失敗執行的方法
    fun onFailure(code: Int)

    //連接成功執行的方法
    fun onResponse(json: String)
}