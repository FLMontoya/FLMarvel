package com.android.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable