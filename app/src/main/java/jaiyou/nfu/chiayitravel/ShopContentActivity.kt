package jaiyou.nfu.chiayitravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jaiyou.nfu.chiayitravel.ShopListActivity
import jaiyou.nfu.chiayitravel.model.ShopModel
import kotlinx.android.synthetic.main.activity_shop_content.*

class ShopContentActivity : AppCompatActivity() {

    private var respData: String = ""
    private var shopContent: List<ShopModel> = ArrayList<ShopModel>()
    private var position: Int = 0
    private val tag = "ShopContentActivity"
    var xxx:Int = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_content)
        getIntentData()
        setupView()
    }

    fun getIntentData(){
        position = intent.extras.getInt("position")
        shopContent = intent.extras.getParcelableArrayList("ShopContent")
        Log.d("ShopContent", shopContent.get(position).toString())
    }

    fun setupView(){
        tv_shop_name.setText(shopContent.get(position).getName())
        tv_shop_description.setText(shopContent.get(position).getDescription())
        tv_shop_phone.setText(shopContent.get(position).getTel())
        tv_shop_address.setText(shopContent.get(position).getAdd())
        tv_shop_website.setText(shopContent.get(position).getWebsite())
        tv_shop_opentime.setText(shopContent.get(position).getOpentime())
    }
}
