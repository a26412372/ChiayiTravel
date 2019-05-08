package jaiyou.nfu.chiayitravel.API

import io.reactivex.Observable
import jaiyou.nfu.chiayitravel.model.Post
import retrofit2.http.GET

interface IMyAPI {
    @get:GET("posts")
    val posts: Observable<List<Post>>
}