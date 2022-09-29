package com.android.marvel.data.dto.event

import com.android.marvel.data.dto.CharacterSummaryDto
import com.android.marvel.data.dto.ComicsSummaryDto
import com.android.marvel.data.dto.CreatorsSummaryDto
import com.android.marvel.data.dto.UrlDto
import com.android.marvel.data.dto.NextDto
import com.android.marvel.data.dto.PreviousDto
import com.android.marvel.data.dto.SeriesSummaryDto
import com.android.marvel.data.dto.StoriesSummaryDto
import com.android.marvel.data.dto.ThumbnailDto
import com.google.gson.annotations.SerializedName

data class EventDto(

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urlDtos: List<UrlDto>,
    @SerializedName("modified") val modified: String,
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String,
    @SerializedName("thumbnail") val thumbnailDto: ThumbnailDto,
    @SerializedName("comics") val comicsSummaryDto: ComicsSummaryDto,
    @SerializedName("stories") val storiesSummaryDto: StoriesSummaryDto,
    @SerializedName("series") val seriesSummaryDto: SeriesSummaryDto,
    @SerializedName("characters") val characterSummaryDto: CharacterSummaryDto,
    @SerializedName("creators") val creatorsSummaryDto: CreatorsSummaryDto,
    @SerializedName("next") val nextDto: NextDto,
    @SerializedName("previous") val previousDto: PreviousDto
)

