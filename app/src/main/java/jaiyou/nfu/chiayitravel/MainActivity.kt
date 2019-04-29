package jaiyou.nfu.chiayitravel

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
        //shoplist_button.setOnClickListener(shoplistButtonClickHandler)
        hotellist_button.setOnClickListener(hotellistButtonClickHandler)
    }

    /*private var shoplistButtonClickHandler = View.OnClickListener { view ->
        Connect("http://10.0.2.2/chiayitravel/shop.php", this, activityTag).getResponse()   //這是取得商店資料用
    }*/

    private var hotellistButtonClickHandler = View.OnClickListener { view ->
        Connect("http://10.0.2.2/chiayitravel/hotel.php", this, activityTag).getResponse()  //這是取得飯店資料用
    }

    /*//將resp到的資料傳到另一個activity
    fun intent(resp: String){
        val intent = Intent(this, ShopListActivity::class.java)
        intent.putExtra("shopList", resp)
        Log.d("shopList", resp)
        startActivity(intent)
    }*/

    //將resp到的資料傳到另一個activity
    fun intent(resp: String){
        val intent = Intent(this, HotelListActivity::class.java)
        intent.putExtra("hotelList", resp)
        Log.d("hotelList", resp)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
