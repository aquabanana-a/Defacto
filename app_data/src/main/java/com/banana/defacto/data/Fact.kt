package com.banana.defacto.data

import android.os.Parcel
import android.os.Parcelable

data class Fact(
    val number: Int,
    val type: NumberType,
    val description: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        NumberType.entries[parcel.readInt()],
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeInt(type.ordinal)
        parcel.writeString(description)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Fact> {
        override fun createFromParcel(parcel: Parcel): Fact = Fact(parcel)
        override fun newArray(size: Int): Array<Fact?> = arrayOfNulls(size)
    }
}