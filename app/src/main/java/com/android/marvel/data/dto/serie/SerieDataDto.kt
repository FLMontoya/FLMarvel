package com.android.marvel.data.dto.serie

import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.Serie
import com.google.gson.annotations.SerializedName


data class SerieDataDto(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") var results: ArrayList<SerieDao> = arrayListOf()
)

fun Collection<SerieDao>.toDetailItem(): List<MarvelItem> {
    return this.map { dto ->
        MarvelItem(
            id = dto.id,
            name = dto.title,
            image = dto.thumbnailDto.getPortrait(),
            marvelItemType = MarvelItemType.SERIE
        )
    }
}

fun Collection<SerieDao>.toModel(): List<Serie> {
    return this.map { dto ->
        Serie(
            id = dto.id,
            name = dto.title,
            description = dto.description,
            imagePath = dto.thumbnailDto.path,
            imageExtension = dto.thumbnailDto.extension,
            comicsCount = dto.comicsSummaryDto.available,
            eventsCount = dto.eventsSummaryDto.available,
            charactersCount = dto.characterSummaryDto.available,
            detailLink = dto.urlDtos.find { it.type == "detail" }?.url,
            previousSerieName = dto.previousDto?.name,
            previousSerieResource = dto.previousDto?.resourceURI,
            nextSerieName = dto.nextDto?.name,
            nextSerieResource = dto.nextDto?.resourceURI
        )
    }
}