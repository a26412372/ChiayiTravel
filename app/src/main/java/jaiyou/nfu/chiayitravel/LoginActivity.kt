package jaiyou.nfu.chiayitravel

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.FacebookActivity
import com.facebook.login.LoginManager
import jaiyou.nfu.chiayitravel.OkHttp.NetCallBack
import jaiyou.nfu.chiayitravel.OkHttp.NetClient
import okhttp3.FormBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.util.*

import kotlin.Exception
import kotlin.collections.ArrayList


class LoginActivity : AppCompatActivity() {

    private val TAG = FacebookActivity::class.java.simpleName
    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //init facebook
        FacebookSdk.sdkInitialize(applicationContext)
        //AppEventsLogger.activateApp(this)
        callbackManager = CallbackManager.Factory.create()

        getHashKey()

        //method_1.判斷用戶是否登入過
        if(Profile.getCurrentProfile() != null) {
            var profile = Profile.getCurrentProfile()
            //取得用戶大頭照
            var userPhoto = profile.getProfilePictureUri(300, 300)
            var id = profile.id
            var name = profile.name
            Log.d(TAG, "Facebook userPhoto: " + userPhoto)
            Log.d(TAG, "Facebook id: " + id)
            Log.d(TAG, "Facebook name: " + name)
        }else{
            loginFaceBook()
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

    fun loginFaceBook(){
        var permissions = ArrayList<String>()
        permissions.add("public_profile")
        permissions.add("email")
        permissions.add("user_friends")

        LoginManager.getInstance().logInWithReadPermissions(this, permissions)

        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                var graphRequest = GraphRequest.newMeRequest(loginResult.accessToken){`object`, response ->
                    try {
                        if (response.connection.responseCode == 200){
                            Log.d(TAG, "Facebook JSONObject:" + `object`)
                            var id = `object`.getLong("id")
                            var name = `object`.getString("name")
                            var email = `object`.getString("email")
                            Log.d(TAG, "Facebook id:" + id)
                            Log.d(TAG, "Facebook name:" + name)
                            Log.d(TAG, "Facebook email" + email)
                            //取得用戶大頭照
                            var proflie = Profile.getCurrentProfile()
                            //設定大頭照大小
                            var userPhoto = proflie.getProfilePictureUri(300, 300)
                            Log.d(TAG, "Facebook userPhoto: " + userPhoto)

                            val requestBody = FormBody.Builder()
                                .add("fbId", id.toString())
                                .add("fbName", name)
                                .add("fbEmail", email)
                                .add("fbPhoto", userPhoto.toString())
                                .build()
                            NetClient.instance
                                .callPostNet("http://10.0.2.2/chiayitravel/fb_member.php", requestBody, object : NetCallBack{
                                override fun onFailure(code: Int) {
                                    Log.d("connect falid", code.toString())
                                }

                                override fun onResponse(json: String) {
                                    Log.d("connect success", "")
                                }
                            })
                            /*Glide.with(this@LoginActivity)
                                .load(userPhoto.toString())
                                .crossFade()
                                .into(mImgPhoto)*/
                            //mTextDescription.setText(String.format(Locale.TAIWAN, "Name:%s\nE-mail:%s", name, email))
                        }
                    }catch (e: Exception){
                    }catch (e: JSONException){
                    }
                }
                // https://developers.facebook.com/docs/android/graph?locale=zh_TW
                // 如果要取得email，需透過添加參數的方式來獲取(如下)
                // 不添加只能取得id & name
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                graphRequest.setParameters(parameters)
                graphRequest.executeAsync()
            }
            override fun onCancel() {
                Log.d(TAG, "Facebook onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "Facebook onError:" + error.toString())
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
