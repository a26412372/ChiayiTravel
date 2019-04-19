package jaiyou.nfu.chiayitravel

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        getHashKey()

        //loginFaceBook()
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
            val info = packageManager.getPackageInfo("jaiyou.nfu.chiayitravel", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures){
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }catch (e: PackageManager.NameNotFoundException){
        }catch (e: NoSuchAlgorithmException){ }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

}
