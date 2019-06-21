package jaiyou.nfu.chiayitravel

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import android.transition.Explode
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.qslll.library.ExpandingPagerFactory
import com.qslll.library.fragments.ExpandingFragment
import jaiyou.nfu.chiayitravel.OkHttp.NetCallBack
import jaiyou.nfu.chiayitravel.OkHttp.NetClient
import jaiyou.nfu.chiayitravel.adapter.HotelViewPagerAdapter
import jaiyou.nfu.chiayitravel.extension.fromJson
import jaiyou.nfu.chiayitravel.model.HotelList
import jaiyou.nfu.chiayitravel.model.HotelModel
import jaiyou.nfu.chiayitravel.model.Travel
import kotlinx.android.synthetic.main.activity_hotel_list.*

class HotelListActivity : AppCompatActivity(), ExpandingFragment.OnExpandingClickListener {

    private var respData: String = ""
    private var hotelList: List<HotelModel> = ArrayList<HotelModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_list)

        setupWindowAnimations()

        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Log.d("thread", "Main Thread");
        } else {
            Log.d("thread", "Not Main Thread");
        }

        //這是新方法，在這個Activity完成網路請求
        NetClient.getInstance().callGetNet("http://10.0.2.2/chiayitravel/hotel.php", object: NetCallBack{
            override fun onFailure(code: Int) {
                Log.d("connect falid", code.toString())
            }

            override fun onResponse(json: String) {
                //val hotel = Gson().fromJson<HotelList>(json)      //解析json

                this@HotelListActivity.runOnUiThread(Runnable {
                    val hotel: HotelList = Gson().fromJson(json)
                    hotelList = hotel.results
                    setupView(hotelList)
                    Log.d("hotel", hotelList.get(0).name)
                })
            }
        })

        //getRespData()
        //setupView(hotelList)
    }


    //這是原來的方法，在上一個Activity完成網路請求後將response結果傳到這個Activity
    private fun getRespData(){
        respData = intent.extras.get("List").toString()         //取得intent過來的json
        val hotel = Gson().fromJson<HotelList>(respData)      //解析json
        hotelList = hotel.results
        Log.d("Hotel", hotelList.toString())
    }

    private fun setupView(hotelList: List<HotelModel>){
        var adapter = HotelViewPagerAdapter(supportFragmentManager)
        adapter.addAll(hotelList)
        viewPager.setAdapter(adapter)

        ExpandingPagerFactory.setupViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val expandingFragment = ExpandingPagerFactory.getCurrentFragment(viewPager)
                if (expandingFragment != null && expandingFragment.isOpenend) {
                    expandingFragment.close()
                }
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun onBackPressed() {
        if (!ExpandingPagerFactory.onBackPressed(viewPager)) {
            super.onBackPressed()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
        val slideTransition = Explode()
        window.reenterTransition = slideTransition
        window.exitTransition = slideTransition
    }

    /*private fun generateHotelList(hotelList: List<HotelModel>): List<HotelModel> {
        //val hotelModel = java.util.ArrayList<HotelModel>()

        return hotelList
    }*/


    private fun startInfoActivity(view: View, hotelModel: HotelModel) {
        val activity = this
        ActivityCompat.startActivity(
            activity,
            InfoActivity.newInstance(activity, hotelModel),
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                Pair(view, getString(R.string.transition_image))
            )
                .toBundle()
        )
    }

    private lateinit var view: View
    override fun onExpandingClick(v: View) {
        //v is expandingfragment layout
        view = v.findViewById(R.id.front_image)
        val hotelModel = hotelList[viewPager.getCurrentItem()]
        //val hotelList = generateHotelList().get(viewPager.currentItem)
        startInfoActivity(view, hotelModel)
    }
}
