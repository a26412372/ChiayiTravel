package jaiyou.nfu.chiayitravel.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.Travel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_front.*


class FragmentTop : Fragment() {

    val ARG_TRAVEL = "ARG_TRAVEL"
    var hotelModel: HotelModel? = null

    companion object {
        fun newInstance(hotelModel: HotelModel?): FragmentTop {
            val ARG_TRAVEL = "ARG_TRAVEL"
            val args = Bundle()
            val fragmentTop = FragmentTop()
            args.putParcelable(ARG_TRAVEL, hotelModel)
            fragmentTop.arguments = args
            return fragmentTop
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            hotelModel = args.getParcelable<HotelModel>(ARG_TRAVEL)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_front, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hotelModel != null) {
            Picasso.get().load(hotelModel!!.getImage()).into(front_image)
            front_title.setText(hotelModel!!.getName())
        }

    }

}
