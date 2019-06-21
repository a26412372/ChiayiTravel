package jaiyou.nfu.chiayitravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jaiyou.nfu.chiayitravel.model.AttractionModel
import jaiyou.nfu.chiayitravel.model.ShopModel
import kotlinx.android.synthetic.main.activity_attraction_content.*
import kotlinx.android.synthetic.main.activity_shop_content.*

class AttractionContentActivity : AppCompatActivity() {

    private var attractionContent: List<AttractionModel> = ArrayList<AttractionModel>()
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction_content)
        getIntentData()
        setupView()
    }

    fun getIntentData(){
        position = intent.extras.getInt("position")
        attractionContent = intent.extras.getParcelableArrayList("AttractionContent")
        Log.d("AttractionContent", attractionContent.get(position).toString())
    }

    fun setupView(){
        tv_attr_name.setText(attractionContent.get(position).name)
        tv_attr_description.setText(attractionContent.get(position).description)
        tv_attr_phone.setText(attractionContent.get(position).telephone)
        tv_attr_address.setText(attractionContent.get(position).address)
        tv_attr_website.setText(attractionContent.get(position).website)
        tv_attr_opentime.setText(attractionContent.get(position).open_time)
    }
}
