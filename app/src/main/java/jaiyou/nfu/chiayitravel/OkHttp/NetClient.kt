package jaiyou.nfu.chiayitravel.OkHttp

import android.util.Log
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient




class NetClient {
    private val client: OkHttpClient

    private constructor(){
        client = initOkHttpClient()
    }

    private fun initOkHttpClient(): OkHttpClient {
        //初始化時定義一些參數
        return OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)//設置讀取超時為10秒
            .connectTimeout(10000, TimeUnit.MILLISECONDS)//設置連接超時為10秒
            .build()
    }

    companion object {
        //Singleton Double Check
        val instance: NetClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NetClient()
        }
    }

    fun callGetNet(url: String, mCallback: NetCallBack) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val call = instance
            .initOkHttpClient()
            .newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //請求網路失敗，调用自己的接口，通过传回的-1可以知道错误类型
                mCallback.onFailure(-1)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                //請求網路成功說明服務器有response回來，但返回的是什麼我們無法確定，可以根據response code判斷
                if (response.code() === 200) {
                        //Log.d("resp", response.body().toString())
                        mCallback.onResponse(response!!.body()!!.string())
                } else {
                    //如果不是200說明異常，調用NetCallBack的失敗方法將response code傳入
                    mCallback.onFailure(response.code())
                }
            }
        })
    }

    fun callPostNet(url: String, requestBody: RequestBody , mCallback: NetCallBack){
        val  request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val call = instance
            .initOkHttpClient()
            .newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                mCallback.onFailure(-1)
            }

            override fun onResponse(call: Call, response: Response) {
                //請求網路成功說明服務器有response回來，但返回的是什麼我們無法確定，可以根據response code判斷
                if (response.code() === 200) {
                    //Log.d("resp", response.body().toString())
                    mCallback.onResponse(response!!.body()!!.string())
                } else {
                    //如果不是200說明異常，調用NetCallBack的失敗方法將response code傳入
                    mCallback.onFailure(response.code())
                }
            }
        })
    }
}