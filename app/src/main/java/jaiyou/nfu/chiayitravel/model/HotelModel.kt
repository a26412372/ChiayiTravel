package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class HotelList(val results: List<HotelModel>)

data class HotelModel(
    private val hotelId:String,
    private val hotelName: String,
    private val hotelDescription: String,
    private val hotelAdd: String,
    private val hotelTel: String,
    private val hotelFax: String,
    private val hotelWebsite: String,
    private val hotelPicture: String,
    private val hotelPicdescribe: String,
    private val hotelPx: String,
    private val hotelPy: String,
    private val hotelChangetime: String
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
        parcel.writeString(hotelId)
        parcel.writeString(hotelName)
        parcel.writeString(hotelDescription)
        parcel.writeString(hotelAdd)
        parcel.writeString(hotelTel)
        parcel.writeString(hotelFax)
        parcel.writeString(hotelWebsite)
        parcel.writeString(hotelPicture)
        parcel.writeString(hotelPicdescribe)
        parcel.writeString(hotelPx)
        parcel.writeString(hotelPy)
        parcel.writeString(hotelChangetime)
    }

    fun getName(): String{
        return this.hotelName
    }

    fun getImage(): String{
        return this.hotelPicture
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<HotelModel>()
    }

}