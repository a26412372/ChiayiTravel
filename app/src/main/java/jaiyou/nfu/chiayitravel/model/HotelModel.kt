package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class HotelList(val results: List<HotelModel>)

data class HotelModel(
    val hotelId:String,
    val hotelName: String,
    val hotelDescription: String,
    val hotelAdd: String,
    val hotelTel: String,
    val hotelFax: String,
    val hotelWebsite: String,
    val hotelPicture: String,
    val hotelPicdescribe: String,
    val hotelPx: String,
    val hotelPy: String,
    val hotelChangetime: String
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

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<HotelModel>()
    }

}