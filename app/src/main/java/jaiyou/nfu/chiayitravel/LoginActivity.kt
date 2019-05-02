package jaiyou.nfu.chiayitravel

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.FacebookActivity
import com.facebook.login.LoginManager


class LoginActivity : AppCompatActivity() {

    private val TAG = FacebookActivity::class.java.simpleName
    private var callbackManager: CallbackManager? = null
    private var loginManager: LoginManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //init facebook
        FacebookSdk.sdkInitialize(applicationContext)
        //AppEventsLogger.activateApp(this)
        loginManager = LoginManager.getInstance()
        callbackManager = CallbackManager.Factory.create()

        getHashKey()

        //loginFaceBook()


        //method_1.判斷用戶是否登入過
        if(Profile.getCurrentProfile() != null){
            var profile = Profile.getCurrentProfile()
            //取得用戶大頭照
            var userPhoto = profile.getProfilePictureUri(300,300)
            var id = profile.id
            var name = profile.name
            Log.d(TAG, "Facebook userPhoto: " + userPhoto)
            Log.d(TAG, "Facebook id: " + id)
            Log.d(TAG, "Facebook name: " + name)
        }

        // method_2.判斷用戶是否登入過
        /*if (AccessToken.getCurrentAccessToken() != null) {
            Log.d(TAG, "Facebook getApplicationId: " + AccessToken.getCurrentAccessToken().getApplicationId());
            Log.d(TAG, "Facebook getUserId: " + AccessToken.getCurrentAccessToken().getUserId());
            Log.d(TAG, "Facebook getExpires: " + AccessToken.getCurrentAccessToken().getExpires());
            Log.d(TAG, "Facebook getLastRefresh: " + AccessToken.getCurrentAccessToken().getLastRefresh());
            Log.d(TAG, "Facebook getToken: " + AccessToken.getCurrentAccessToken().getToken());
            Log.d(TAG, "Facebook getSource: " + AccessToken.getCurrentAccessToken().getSource());
        }*/
    }

    private fun loginFaceBook(){
        val permissions = ArrayList<String>()
        permissions.add("public_profile")
        permissions.add("email")
        permissions.add("user_friends")

        loginManager!!.logInWithReadPermissions(this, permissions)
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult?) {

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
