package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class ShopList(val results: List<ShopModel>)

data class ShopModel(
    val shopId: String,
    val shopName: String,
    val shopDescription: String,
    val shopAdd: String,
    val shopTel: String,
    val shopOpentime: String,
    val shopWebsite: String,
    val shopPicture: String,
    val shopPicdescribe: String,
    val shopPx: String,
    val shopPy: String,
    val shopChangetime: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(shopId)
        parcel.writeString(shopName)
        parcel.writeString(shopDescription)
        parcel.writeString(shopAdd)
        parcel.writeString(shopTel)
        parcel.writeString(shopOpentime)
        parcel.writeString(shopWebsite)
        parcel.writeString(shopPicture)
        parcel.writeString(shopPicdescribe)
        parcel.writeString(shopPx)
        parcel.writeString(shopPy)
        parcel.writeString(shopChangetime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<ShopModel>()
    }
}
