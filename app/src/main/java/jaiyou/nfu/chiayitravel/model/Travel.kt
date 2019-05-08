package jaiyou.nfu.chiayitravel.model

import android.os.Parcel
import android.os.Parcelable
import jaiyou.nfu.chiayitravel.extension.parcelableCreatorOf

class Travel : Parcelable {
    private var name: String
    private var image: Int

    constructor(name: String, image: Int) {
        this.name = name
        this.image = image
    }

    protected constructor(parcel: Parcel) {
        name = parcel.readString()
        image = parcel.readInt()
    }

    fun getImage(): Int{
        return this.image
    }

    fun getName(): String{
        return this.name
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(image)
    }

    companion object {
        @JvmField val CREATOR = parcelableCreatorOf<Travel>()
    }
}