package jaiyou.nfu.chiayitravel

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.Travel
import kotlinx.android.synthetic.main.activity_info.*


class InfoActivity : AppCompatActivity() {

    companion object{
        private val EXTRA_TRAVEL = "EXTRA_TRAVEL"
        fun newInstance(context: Context?, hotelModel: HotelModel?): Intent {
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra(EXTRA_TRAVEL, hotelModel)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        var hotelModel = intent.getParcelableExtra<HotelModel>(EXTRA_TRAVEL)
        if (hotelModel != null){
            Picasso.get().load(hotelModel.getImage()).into(info_image)
            info_title.setText(hotelModel.getName())
        }
    }
}
