package com.android.marvel.data.dto.serie

import com.android.marvel.data.dto.CharacterSummaryDto
import com.android.marvel.data.dto.ComicsSummaryDto
import com.android.marvel.data.dto.CreatorsSummaryDto
import com.android.marvel.data.dto.EventsSummaryDto
import com.android.marvel.data.dto.MarvelUrlDto
import com.android.marvel.data.dto.NextDto
import com.android.marvel.data.dto.PreviousDto
import com.android.marvel.data.dto.StoriesSummaryDto
import com.android.marvel.data.dto.ThumbnailDto
import com.google.gson.annotations.SerializedName


data class SerieDao(

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urlDtos: ArrayList<MarvelUrlDto>,
    @SerializedName("startYear") val startYear: String,
    @SerializedName("endYear") val endYear: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnailDto: ThumbnailDto,
    @SerializedName("comics") val comicsSummaryDto: ComicsSummaryDto,
    @SerializedName("stories") val storiesSummaryDto: StoriesSummaryDto,
    @SerializedName("events") val eventsSummaryDto: EventsSummaryDto,
    @SerializedName("characters") val characterSummaryDto: CharacterSummaryDto,
    @SerializedName("creators") val creatorsSummaryDto: CreatorsSummaryDto,
    @SerializedName("next") val nextDto: NextDto,
    @SerializedName("previous") val previousDto: PreviousDto

)
