package jaiyou.nfu.chiayitravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jaiyou.nfu.chiayitravel.model.HotelModel

class HotelListActivity : AppCompatActivity() {

    private var respData: String = ""
    private var hotelList: List<HotelModel>? = ArrayList<HotelModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_list)
    }

    fun getRespData(){

    }
}
