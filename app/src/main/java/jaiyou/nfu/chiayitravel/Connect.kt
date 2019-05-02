package jaiyou.nfu.chiayitravel


import android.app.Activity
import android.content.Context
import android.util.Log

import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

class Connect {
    private var mStartActivityTag: String = ""   //用來判斷是哪個 Activity 呼叫連線
    private var mEndActivityTag: Class<*>
    private var responseData: String = ""
    private var mContext: Context? = null
    private var mUrl: String = ""
    private var mRequestBody: RequestBody? = null
    private var mRequest: Request? = null

    //GET
    constructor(url: String, context: Context, startActivityTag: String, endActivityTag: Class<*>){
        this.mUrl = url
        this.mContext = context
        this.mStartActivityTag = startActivityTag
        this.mEndActivityTag = endActivityTag
    }

    //POST
    constructor(url: String, requestBody: RequestBody, context: Context, startActivityTag: String, endActivityTag: Class<*>){
        this.mUrl = url
        this.mRequestBody = requestBody
        this.mContext = context
        this.mStartActivityTag = startActivityTag
        this.mEndActivityTag = endActivityTag
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
                    intent(mStartActivityTag, mEndActivityTag)
                }
            })
        }catch (e: Exception){
            Log.d("connect exception", e.message)
            e.printStackTrace()
        }
    }

    //這邊是送resp的資料到activity
    fun intent(startActivityTag: String, endActivityTag: Class<*>){
        when(startActivityTag){
            "MainActivity" -> (mContext as MainActivity).intent(responseData, endActivityTag)
            "ShopListActivity" -> (mContext as ShopListActivity).intent(responseData)
        }
    }
}
