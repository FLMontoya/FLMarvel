package com.android.marvel.data.dto.comic

import com.android.marvel.data.dto.CharacterSummaryDto
import com.android.marvel.data.dto.CreatorsSummaryDto
import com.android.marvel.data.dto.EventsSummaryDto
import com.android.marvel.data.dto.MarvelUrlDto
import com.android.marvel.data.dto.SeriesSummaryDto
import com.android.marvel.data.dto.StoriesSummaryDto
import com.android.marvel.data.dto.ThumbnailDto
import com.google.gson.annotations.SerializedName

data class ComicDto(

    @SerializedName("creators")
    val creatorsSummaryDto: CreatorsSummaryDto,

    @SerializedName("issueNumber")
    val issueNumber: Int,

    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("variants")
    val variants: List<Any?>,

    @SerializedName("title")
    val title: String,

    @SerializedName("diamondCode")
    val diamondCode: String,

    @SerializedName("characters")
    val characterSummaryDto: CharacterSummaryDto,

    @SerializedName("urls")
    val urlDtos: List<MarvelUrlDto>,

    @SerializedName("ean")
    val ean: String,

    @SerializedName("collections")
    val collections: List<Any?>,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("prices")
    val prices: List<Prices>,

    @SerializedName("events")
    val eventsSummaryDto: EventsSummaryDto,

    @SerializedName("collectedIssues")
    val collectedIssues: List<Any?>,

    @SerializedName("pageCount")
    val pageCount: Int,

    @SerializedName("thumbnail")
    val thumbnailDto: ThumbnailDto,

    @SerializedName("images")
    val images: List<Any?>,

    @SerializedName("stories")
    val storiesSummaryDto: StoriesSummaryDto,

    @SerializedName("textObjects")
    val textObjects: List<TextObjects>,

    @SerializedName("digitalId")
    val digitalId: Int,

    @SerializedName("format")
    val format: String,

    @SerializedName("upc")
    val upc: String,

    @SerializedName("dates")
    val dates: List<Dates>,

    @SerializedName("resourceURI")
    val resourceURI: String,

    @SerializedName("variantDescription")
    val variantDescription: String,

    @SerializedName("issn")
    val issn: String,

    @SerializedName("series")
    val seriesSummaryDto: SeriesSummaryDto
)

data class Images(

    @SerializedName("path")
    val path: String,

    @SerializedName("extension")
    val extension: String
)

data class Prices(

    @SerializedName("price")
    val price: Double,

    @SerializedName("type")
    val type: String
)

data class Dates(

    @SerializedName("date")
    val date: String,

    @SerializedName("type")
    val type: String
)

data class TextObjects(

    @SerializedName("language")
    val language: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("type")
    val type: String
)