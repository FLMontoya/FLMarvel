package com.android.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    override val id: Int,
    override val name: String,
    override val description: String?,
    override val imagePath: String,
    override val imageExtension: String,
    override val comicsCount: Int = 0,
    override val eventsCount: Int = 0,
    override val seriesCount: Int = 0,
    override val charactersCount: Int = 0,
    override val detailLink: String? = null,
    val numPages: String,
    val price: String
) : MarvelModel(), Parcelable
