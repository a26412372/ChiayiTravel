package jaiyou.nfu.chiayitravel.API

import io.reactivex.Observable
import jaiyou.nfu.chiayitravel.model.AttractionModel
import retrofit2.http.GET

interface IMyAPI {
    @get:GET("attraction.php")
    val posts: Observable<List<AttractionModel>>
}