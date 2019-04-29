package jaiyou.nfu.chiayitravel.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.qslll.library.ExpandingViewPagerAdapter
import jaiyou.nfu.chiayitravel.fragments.HotelExpandingFragment
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.ShopModel
import jaiyou.nfu.chiayitravel.model.Travel
import java.util.ArrayList

class HotelViewPagerAdapter(fm: FragmentManager) : ExpandingViewPagerAdapter(fm) {

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
