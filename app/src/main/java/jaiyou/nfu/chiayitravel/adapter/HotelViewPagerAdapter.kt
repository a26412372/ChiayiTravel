package jaiyou.nfu.chiayitravel.adapter

import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.util.Pair
import android.view.View
import com.qslll.library.ExpandingViewPagerAdapter
import com.qslll.library.fragments.ExpandingFragment
import jaiyou.nfu.chiayitravel.InfoActivity
import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.fragments.HotelExpandingFragment
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.ShopModel
import jaiyou.nfu.chiayitravel.model.Travel
import kotlinx.android.synthetic.main.activity_hotel_list.*
import java.util.ArrayList


class HotelViewPagerAdapter(fm: FragmentManager) : ExpandingViewPagerAdapter(fm){

    var hotels: MutableList<HotelModel>

    init {
        super.getCurrentFragment()
        hotels = ArrayList()
    }

    fun addAll(hotels: List<HotelModel>) {
        this.hotels.addAll(hotels)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        val hotel = hotels[position]
        return HotelExpandingFragment.newInstance(hotel)
    }

    override fun getCount(): Int {
        return hotels.size
    }




}
