package jaiyou.nfu.chiayitravel


import android.content.Context
import android.util.Log

import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

class Connect {
    var mTag: String = ""   //用來判斷是哪個 Activity 呼叫連線
    var responseData: String = ""
    var mContext: Context? = null
    var mUrl: String = ""
    var mRequestBody: RequestBody? = null
    var mRequest: Request? = null

    //GET
    constructor(url: String, context: Context, tag: String){
        this.mUrl = url
        this.mContext = context
        this.mTag = tag
    }

    //POST
    constructor(url: String, requestBody: RequestBody, context: Context, tag: String){
        this.mUrl = url
        this.mRequestBody = requestBody
        this.mContext = context
        this.mTag = tag
    }

    fun getResponse(){
        try {
            val mClient: OkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
            mRequest = Request.Builder()
                .url(mUrl)
                .get()
                .build()

            val mCall: Call = mClient.newCall(mRequest)

            mCall.enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("connect falid", e.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    //Log.d("resp", response!!.body()!!.string())
                    responseData = response!!.body()!!.string()
                    intent(mTag)
                }
            })
        }catch (e: Exception){
            Log.d("connect exception", e.message)
            e.printStackTrace()
        }
    }

    //這邊是送resp的資料到activity
    fun intent(activityTag: String){
        when(activityTag){
            "MainActivity" -> (mContext as MainActivity).intent(responseData)
            "ShopListActivity" -> (mContext as ShopListActivity).intent(responseData)
        }
    }
}
