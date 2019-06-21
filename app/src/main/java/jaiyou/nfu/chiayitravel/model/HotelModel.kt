package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class HotelList(val results: List<HotelModel>)

data class HotelModel(
    val hotelId:String,
    val name: String,
    val description: String,
    val address: String,
    val telephone: String,
    val website: String,
    val picture: String,
    val Px: String,
    val Py: String,
    val ownerId: Int
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
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hotelId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(address)
        parcel.writeString(telephone)
        parcel.writeString(website)
        parcel.writeString(picture)
        parcel.writeString(Px)
        parcel.writeString(Py)
        parcel.writeInt(ownerId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<HotelModel>()
    }

}