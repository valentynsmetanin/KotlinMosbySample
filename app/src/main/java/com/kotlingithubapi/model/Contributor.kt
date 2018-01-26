package com.kotlingithubapi.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Valentyn on 16.07.2017.
 */
@Entity
data class Contributor(
        @Id(assignable = true) var id: Long,
        val login: String,
        @SerializedName("html_url") val url: String?,
        val contributions: Int? = 0,
        @SerializedName("avatar_url") val avatarUrl: String?,
        val name: String?,
        val company: String?,
        val email: String?,
        val location: String?) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(login)
        parcel.writeString(url)
        parcel.writeValue(contributions)
        parcel.writeString(avatarUrl)
        parcel.writeString(name)
        parcel.writeString(company)
        parcel.writeString(email)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contributor> {
        override fun createFromParcel(parcel: Parcel): Contributor {
            return Contributor(parcel)
        }

        override fun newArray(size: Int): Array<Contributor?> {
            return arrayOfNulls(size)
        }
    }


}