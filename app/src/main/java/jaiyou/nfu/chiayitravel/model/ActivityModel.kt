package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class ActivityList(val results: List<ActivityModel>)

//data class val is read-only, so It just has default getter method
//data class var is public property, so It has default getter and setter method
data class ActivityModel (
    val activityId: String,
    val name: String,
    val description: String,
    val start_time: String,
    val end_time: String,
    val telephone: String,
    val website: String,
    val picture: String,
    val ownerId: Int
): Parcelable{

    constructor(parcel: Parcel): this(
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
        parcel.writeString(activityId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(start_time)
        parcel.writeString(end_time)
        parcel.writeString(telephone)
        parcel.writeString(website)
        parcel.writeString(picture)
        parcel.writeInt(ownerId)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<AttractionModel>()
    }

}