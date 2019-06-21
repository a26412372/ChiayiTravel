package jaiyou.nfu.chiayitravel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jaiyou.nfu.chiayitravel.API.IMyAPI
import jaiyou.nfu.chiayitravel.Retrofit.RetrofitClient
import jaiyou.nfu.chiayitravel.adapter.AttractionListAdapter
import jaiyou.nfu.chiayitravel.model.AttractionModel

import kotlinx.android.synthetic.main.activity_attraction_list.*

class AttractionListActivity : AppCompatActivity() {

    internal lateinit var jsonApi: IMyAPI
    internal lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction_list)

        //init API
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyAPI::class.java)
        compositeDisposable = CompositeDisposable()

        //view
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { attrList->displayData(attrList) })
    }

    private fun displayData(attrList: List<AttractionModel>?) {
        Log.d("attrList", attrList!!.toString())
        val adapter = AttractionListAdapter(this, attrList!!)
        adapter.setOnItemClickListener(object: AttractionListAdapter.OnItemClickListener{
            override fun onItemClickListener(position: Int) {
                val intent = Intent(this@AttractionListActivity, AttractionContentActivity::class.java)
                intent.putParcelableArrayListExtra("AttractionContent", attrList as java.util.ArrayList<out Parcelable>)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })
        recycler_posts.adapter = adapter
    }


}
