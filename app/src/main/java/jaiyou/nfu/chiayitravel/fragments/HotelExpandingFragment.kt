package jaiyou.nfu.chiayitravel.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qslll.library.fragments.ExpandingFragment

import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.Travel


class HotelExpandingFragment: ExpandingFragment() {

    internal val ARG_TRAVEL = "ARG_TRAVEL"
    internal var hotelModel: HotelModel? = null

    companion object{
        fun newInstance(hotelModel: HotelModel?): HotelExpandingFragment{
            val ARG_TRAVEL = "ARG_TRAVEL"
            val fragment = HotelExpandingFragment()
            val args = Bundle()
            args.putParcelable(ARG_TRAVEL, hotelModel)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            hotelModel = args.getParcelable(ARG_TRAVEL)
        }
    }

    /**
     * include TopFragment
     * @return
     */
    override fun getFragmentTop(): Fragment {
        return FragmentTop.newInstance(hotelModel)
    }

    /**
     * include BottomFragment
     * @return
     */
    override fun getFragmentBottom(): Fragment {
        return FragmentBottom.newInstance(hotelModel)
    }
}
