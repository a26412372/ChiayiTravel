package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class FBMemberList(val results: List<HotelModel>)

data class FBMemberModel(
    val fbId:String,
    val fbName: String,
    val fbEmail: String,
    val fbPhoto: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fbId)
        parcel.writeString(fbName)
        parcel.writeString(fbEmail)
        parcel.writeString(fbPhoto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<HotelModel>()
    }

}