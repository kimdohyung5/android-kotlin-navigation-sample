package com.kimdo.good.models

import android.os.Parcel
import android.os.Parcelable

data class Animal(
    val diet: String,
    val image: String,
    val lifespan: String,
    val location: String,
    val name: String,
    val speed: Speed,
    val taxonomy: Taxonomy
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Speed::class.java.classLoader)!!,
        parcel.readParcelable(Taxonomy::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeString(diet)
        parcel?.writeString(image)
        parcel?.writeString(lifespan)
        parcel?.writeString(location)
        parcel?.writeString(name)
        parcel?.writeParcelable(speed, flags)
        parcel?.writeParcelable(taxonomy, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}