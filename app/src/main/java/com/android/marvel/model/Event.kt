package com.android.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String,
    val comicsCount: Int,
    val eventsCount: Int,
    val seriesCount: Int,
    val characterCount: Int,
    val detailLink: String? = null
) : Parcelable