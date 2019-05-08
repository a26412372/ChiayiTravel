package jaiyou.nfu.chiayitravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jaiyou.nfu.chiayitravel.API.IMyAPI
import jaiyou.nfu.chiayitravel.Retrofit.RetrofitClient
import jaiyou.nfu.chiayitravel.adapter.PostAdapter
import jaiyou.nfu.chiayitravel.model.Post
import kotlinx.android.synthetic.main.activity_attraction.*

class AttractionActivity : AppCompatActivity() {

    internal lateinit var jsonApi: IMyAPI
    internal lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction)

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
            .subscribe{posts->displayData(posts)})
    }

    private fun displayData(posts: List<Post>?) {
        Log.d("posts", posts!!.get(0).title)
        val adapter = PostAdapter(this, posts!!)
        recycler_posts.adapter = adapter
    }
}
