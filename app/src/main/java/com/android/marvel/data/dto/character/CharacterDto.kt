package com.android.marvel.data.dto.character

import com.android.marvel.data.dto.ComicsSummaryDto
import com.android.marvel.data.dto.EventsSummaryDto
import com.android.marvel.data.dto.MarvelUrlDto
import com.android.marvel.data.dto.SeriesSummaryDto
import com.android.marvel.data.dto.StoriesSummaryDto
import com.android.marvel.data.dto.ThumbnailDto
import com.google.gson.annotations.SerializedName

data class CharacterDto(

    @SerializedName("thumbnail")
    val thumbnailDto: ThumbnailDto,
    @SerializedName("stories")
    val storiesSummaryDto: StoriesSummaryDto,
    @SerializedName("series")
    val seriesSummaryDto: SeriesSummaryDto,
    @SerializedName("comics")
    val comicsSummaryDto: ComicsSummaryDto,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("resourceURI")
    val resourceURI: String,

    @SerializedName("events")
    val eventsSummaryDto: EventsSummaryDto,
    @SerializedName("urls")
    val urlDtoList: List<MarvelUrlDto>
)
