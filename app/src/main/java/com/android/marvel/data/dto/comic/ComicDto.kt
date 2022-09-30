package com.android.marvel.data.dto.comic

import com.android.marvel.data.dto.CharacterSummaryDto
import com.android.marvel.data.dto.CreatorsSummaryDto
import com.android.marvel.data.dto.EventsSummaryDto
import com.android.marvel.data.dto.Prices
import com.android.marvel.data.dto.SeriesSummaryDto
import com.android.marvel.data.dto.StoriesSummaryDto
import com.android.marvel.data.dto.ThumbnailDto
import com.android.marvel.data.dto.UrlDto
import com.google.gson.annotations.SerializedName

data class ComicDto(

    @SerializedName("creators") val creatorsSummaryDto: CreatorsSummaryDto,
    @SerializedName("issueNumber") val issueNumber: Int,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("description") val description: String?,
    @SerializedName("title") val title: String,
    @SerializedName("diamondCode") val diamondCode: String,
    @SerializedName("characters") val characterSummaryDto: CharacterSummaryDto,
    @SerializedName("urls") val urlDtos: List<UrlDto>,
    @SerializedName("ean") val ean: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("id") val id: Int,
    @SerializedName("prices") val prices: List<Prices>,
    @SerializedName("events") val eventsSummaryDto: EventsSummaryDto,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("thumbnail") val thumbnailDto: ThumbnailDto,
    @SerializedName("stories") val storiesSummaryDto: StoriesSummaryDto,
    @SerializedName("digitalId") val digitalId: Int,
    @SerializedName("format") val format: String,
    @SerializedName("upc") val upc: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("variantDescription") val variantDescription: String,
    @SerializedName("issn") val issn: String,
    @SerializedName("series") val seriesSummaryDto: SeriesSummaryDto
)