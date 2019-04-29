package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class ShopList(val results: List<ShopModel>)

data class ShopModel(
    private val shopId: String,
    private val shopName: String,
    private val shopDescription: String,
    private val shopAdd: String,
    private val shopTel: String,
    private val shopOpentime: String,
    private val shopWebsite: String,
    private val shopPicture: String,
    private val shopPicdescribe: String,
    private val shopPx: String,
    private val shopPy: String,
    private val shopChangetime: String
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

    fun getImage(): String{
        return this.shopPicture
    }

    fun getName(): String{
        return this.shopName
    }

    fun getTel(): String{
        return this.shopTel
    }

    fun getAdd(): String{
        return this.shopAdd
    }

    fun getDescription(): String{
        return this.shopDescription
    }

    fun getWebsite(): String{
        return this.shopWebsite
    }

    fun getOpentime(): String{
        return this.shopOpentime
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<ShopModel>()
    }
}
