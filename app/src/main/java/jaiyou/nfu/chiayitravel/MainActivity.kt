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

    private var callbackManager: CallbackManager? = null
    private val activityTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        val goodMutSet: Set<String> = setOf("iPhone8", "Mate10", "小米6")
        var desc = ""

        goodMutSet.forEach { desc += it + "\n" }
        textView.text = desc


        setupViews()
        getHashKey()
        //loginFaceBook()
    }

    private fun setupViews(){
        shoplist_button.setOnClickListener(shoplistButtonClickHandler)
    }

    private var shoplistButtonClickHandler = View.OnClickListener { view ->
        Connect("http://10.0.2.2/chiayitravel/shop.php", this, activityTag).getResponse()
    }

    fun intent(resp: String){
        val intent = Intent(this, ShopListActivity::class.java)
        intent.putExtra("shopList", resp)
        Log.d("shopList", resp)
        startActivity(intent)
    }

    private fun loginFaceBook(){
        callbackManager = CallbackManager.Factory.create()
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                textView.text = "Login Success ${result?.accessToken?.userId}"// + "${result?.accessToken?.token}"
            }

            override fun onCancel() {
                textView.text = "Login Canceled"
            }

            override fun onError(error: FacebookException?) {

            }
        })
    }

    private fun getHashKey(){
        try {
            val info = packageManager.getPackageInfo("jaiyou.nfu.chiayitravel",PackageManager.GET_SIGNATURES)
            for (signature in info.signatures){
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT))
            }
        }catch (e: PackageManager.NameNotFoundException){
        }catch (e: NoSuchAlgorithmException){ }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menu01 -> tv_01.text = "menu1"
            R.id.menu02 -> tv_01.text = "menu2"
        }
        return super.onOptionsItemSelected(item)
    }*/
}
