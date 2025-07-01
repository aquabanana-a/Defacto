package com.banana.defacto.data

import android.os.Parcel
import android.os.Parcelable

enum class NumberType : Parcelable {
    Trivia,
    Year,
    Math;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NumberType> {
        override fun createFromParcel(parcel: Parcel): NumberType {
            val ordinal = parcel.readInt()
            return values().getOrElse(ordinal) { Trivia }
        }

        override fun newArray(size: Int): Array<NumberType?> = arrayOfNulls(size)

        fun fromKey(key: String): NumberType = when (key.lowercase()) {
            "trivia" -> Trivia
            "year" -> Year
            "math" -> Math
            else -> throw IllegalArgumentException("Unknown key: $key")
        }
    }
}