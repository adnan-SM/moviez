package com.moviez.app.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Movie(
    @SerializedName("imdbID") @PrimaryKey val id: String,
    @SerializedName("Title") var title: String,
    @SerializedName("Year") var year: String,
    @SerializedName("Type") var type: String,
    @SerializedName("Poster") var image: String
) : Parcelable
