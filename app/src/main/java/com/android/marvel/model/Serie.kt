package com.android.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable