package com.android.marvel.data.dto.comic

import com.android.marvel.model.Comic
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.google.gson.annotations.SerializedName

data class ComicDataDto(

    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<ComicDto>
)

fun Collection<ComicDto>.toDetailItem(): List<MarvelItem> {
    return this.map { dto ->
        MarvelItem(
            id = dto.id,
            name = dto.title,
            image = dto.thumbnailDto.getPortrait(),
            marvelItemType = MarvelItemType.COMIC
        )
    }
}

fun Collection<ComicDto>.toModel(): List<Comic> {
    return this.map { dto ->
        Comic(
            id = dto.id,
            name = dto.title,
            description = dto.description,
            imagePath = dto.thumbnailDto.path,
            imageExtension = dto.thumbnailDto.extension,
            eventsCount = dto.eventsSummaryDto.available,
            seriesCount = dto.seriesSummaryDto.available,
            charactersCount = dto.characterSummaryDto.available,
            detailLink = dto.urlDtos.find { it.type == "detail" }?.url,
            numPages = dto.pageCount.toString(),
            price = dto.prices.first().price.toString(),
        )
    }
}