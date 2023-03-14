package com.adityafakhri.premierleaguestadium

import android.os.Parcel
import android.os.Parcelable

data class Stadium(
    val name: String?,
    val description: String?,
    val photo: Int,
    val team: String?,
    val capacity: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(photo)
        parcel.writeString(team)
        parcel.writeInt(capacity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stadium> {
        override fun createFromParcel(parcel: Parcel): Stadium {
            return Stadium(parcel)
        }

        override fun newArray(size: Int): Array<Stadium?> {
            return arrayOfNulls(size)
        }
    }
}