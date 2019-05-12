package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

data class AttracttionList(val results: List<AttractionModel>)

data class AttractionModel (
    private val attId: String,
    private val attName: String,
    private val attDescription: String,
    private val attAdd: String,
    private val attTel: String,
    private val attOpentime: String,
    private val attWebsite: String,
    private val attPicture: String,
    private val attPicdescribe: String,
    private val attPx: String,
    private val attPy: String,
    private val attChangetime: String
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
        parcel.writeString(attId)
        parcel.writeString(attName)
        parcel.writeString(attDescription)
        parcel.writeString(attAdd)
        parcel.writeString(attTel)
        parcel.writeString(attOpentime)
        parcel.writeString(attWebsite)
        parcel.writeString(attPicture)
        parcel.writeString(attPicdescribe)
        parcel.writeString(attPx)
        parcel.writeString(attPy)
        parcel.writeString(attChangetime)
    }

    fun getImage(): String{
        return this.attPicture
    }

    fun getName(): String{
        return this.attName
    }

    fun getTel(): String{
        return this.attTel
    }

    fun getAdd(): String{
        return this.attAdd
    }

    fun getDescription(): String{
        return this.attDescription
    }

    fun getWebsite(): String{
        return this.attWebsite
    }

    fun getOpentime(): String{
        return this.attOpentime
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField val CREATOR = parcelableCreatorOf<AttractionModel>()
    }
}