package jaiyou.nfu.chiayitravel.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import jaiyou.nfu.chiayitravel.OkHttp.NetCallBack
import jaiyou.nfu.chiayitravel.OkHttp.NetClient

import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.extension.fromJson
import jaiyou.nfu.chiayitravel.model.HotelList
import jaiyou.nfu.chiayitravel.model.HotelModel
import kotlinx.android.synthetic.main.fragment_bottom.*
import kotlinx.android.synthetic.main.fragment_front.*


class FragmentBottom : Fragment() {

    val ARG_TRAVEL = "ARG_TRAVEL"
    var hotelModel: HotelModel? = null

    companion object{
        fun newInstance(hotelModel: HotelModel?): FragmentBottom {
            val ARG_TRAVEL = "ARG_TRAVEL"
            val args = Bundle()
            val fragmentBottom = FragmentBottom()
            args.putParcelable(ARG_TRAVEL, hotelModel)
            fragmentBottom.arguments = args
            return fragmentBottom
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        Log.d("args", args.toString())
        if (args != null) {
            hotelModel = args.getParcelable<HotelModel>(ARG_TRAVEL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hotelModel != null) {
            //Picasso.get().load(hotelModel!!.picture).into(front_image)
            button_title.setText(hotelModel!!.address)
        }

    }
}
