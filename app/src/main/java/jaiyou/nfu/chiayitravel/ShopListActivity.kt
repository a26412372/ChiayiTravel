package jaiyou.nfu.chiayitravel

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import jaiyou.nfu.chiayitravel.OkHttp.NetCallBack
import jaiyou.nfu.chiayitravel.OkHttp.NetClient
import jaiyou.nfu.chiayitravel.adapter.ShopListAdapter
import jaiyou.nfu.chiayitravel.extension.fromJson
import jaiyou.nfu.chiayitravel.model.*
import kotlinx.android.synthetic.main.activity_shop_list.*

class ShopListActivity : AppCompatActivity(){

    private var respData: String = ""
    private var shopList: List<ShopModel>? = ArrayList<ShopModel>()
    private val activityTag = "ShopListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)

        NetClient.instance.callGetNet("http://10.0.2.2/chiayitravel/shop.php", object: NetCallBack{
            override fun onFailure(code: Int) {
                Log.d("connect falid", code.toString())
            }

            override fun onResponse(json: String) {
                //下面兩種方法都可以
                //val shop = Gson().fromJson<ShopList>(json)
                val shop: ShopList = Gson().fromJson(json)
                shopList = shop.results
                this@ShopListActivity.runOnUiThread(Runnable {
                    setupView()
                })
                Log.d("shop", json)
            }
        })
    }

    /*private fun getRespData(){
        respData = intent.extras.get("List").toString()         //取得intent過來的json
        val shop = Gson().fromJson<ShopList>(respData)      //解析json
        shopList = shop.results
        Log.d("Shop", shopList.toString())
    }*/

    fun setupView(){
        shopListRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ShopListAdapter(shopList!!)

        adapter.setOnItemClickListener(object : ShopListAdapter.OnItemClickListener{
            override fun onItemClickListener(position: Int) {
                val intent = Intent(this@ShopListActivity, ShopContentActivity::class.java)
                intent.putParcelableArrayListExtra("ShopContent", shopList as java.util.ArrayList<out Parcelable>)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })

        shopListRecyclerView.adapter = adapter
    }

    fun intent(respData: String){
        val intent = Intent(this, ShopContentActivity::class.java)
        intent.putExtra("shopContent", respData)
        Log.d("shopContent", respData)
        startActivity(intent)
    }

}

