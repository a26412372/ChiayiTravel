package jaiyou.nfu.chiayitravel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import jaiyou.nfu.chiayitravel.OkHttp.NetCallBack
import jaiyou.nfu.chiayitravel.OkHttp.NetClient
import jaiyou.nfu.chiayitravel.extension.fromJson
import jaiyou.nfu.chiayitravel.model.AttractionModel
import jaiyou.nfu.chiayitravel.model.AttracttionList
import kotlinx.android.synthetic.main.activity_main.*;
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    private val activityTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

    }

    private fun setupViews(){
        shoplist_button.setOnClickListener(shoplistButtonClickListener)
        hotellist_button.setOnClickListener(hotellistButtonClickListener)
        attrlist_button.setOnClickListener(attrListButtonClickListener)
    }

    private var attrListButtonClickListener = View.OnClickListener {
        startActivity(Intent().setClass(this, AttractionListActivity::class.java))
    }

    private var shoplistButtonClickListener = View.OnClickListener { view ->
        //Connect("http://10.0.2.2/chiayitravel/shop.php", this, activityTag, ShopListActivity::class.java).getResponse()   //這是取得商店資料用
        startActivity(Intent().setClass(this, ShopListActivity::class.java))
    }

    private var hotellistButtonClickListener = View.OnClickListener { view ->
        //Connect("http://10.0.2.2/chiayitravel/hotel.php", this, activityTag, HotelListActivity::class.java).getResponse()  //這是取得飯店資料用
        startActivity(Intent().setClass(this, HotelListActivity::class.java))
    }



    //將resp到的資料傳到另一個activity
    fun intent(respData: String, endActivity: Class<*>){
        val intent = Intent(this, endActivity)
        intent.putExtra("List", respData)
        Log.d("List", respData)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
