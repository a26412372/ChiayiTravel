package jaiyou.nfu.chiayitravel.OkHttp

import android.util.Log
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient




class NetClient {
    val client: OkHttpClient

    private constructor(){
        client = initOkHttpClient()
    }

    private fun initOkHttpClient(): OkHttpClient {
        //初始化的时候可以自定义一些参数
        return OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)//设置读取超时为10秒
            .connectTimeout(10000, TimeUnit.MILLISECONDS)//设置链接超时为10秒
            .build()
    }

    companion object {
        private var instance: NetClient? = null
        fun getInstance(): NetClient {
            if (instance == null) {
                instance = NetClient()
            }
            return instance!!
        }
    }

    fun callGetNet(url: String, mCallback: NetCallBack) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val call = getInstance()
            .initOkHttpClient()
            .newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //请求网络失败，调用自己的接口，通过传回的-1可以知道错误类型
                mCallback.onFailure(-1)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                //请求网络成功说明服务器有响应，但返回的是什么我们无法确定，可以根据响应码判断
                if (response.code() === 200) {
                        //Log.d("resp", response.body().toString())
                        mCallback.onResponse(response!!.body()!!.string())
                } else {
                    //如果不是200说明异常，调用MyCallBack的失败方法将响应码传入
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
        val call = getInstance()
            .initOkHttpClient()
            .newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                mCallback.onFailure(-1)
            }

            override fun onResponse(call: Call, response: Response) {
                //请求网络成功说明服务器有响应，但返回的是什么我们无法确定，可以根据响应码判断
                if (response.code() === 200) {
                    //Log.d("resp", response.body().toString())
                    mCallback.onResponse(response!!.body()!!.string())
                } else {
                    //如果不是200说明异常，调用MyCallBack的失败方法将响应码传入
                    mCallback.onFailure(response.code())
                }
            }
        })
    }
}