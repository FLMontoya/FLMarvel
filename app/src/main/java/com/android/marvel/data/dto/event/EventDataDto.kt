package com.android.marvel.data.dto.event

import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.google.gson.annotations.SerializedName


data class EventDataDto(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<EventDto>
)

fun Collection<EventDto>.toDetailItem(): List<MarvelItem> {
    return this.map { dto ->
        MarvelItem(
            id = dto.id,
            name = dto.title,
            image = dto.thumbnailDto.getPortrait(),
            marvelItemType = MarvelItemType.EVENT
        )
    }
}

fun Collection<EventDto>.toModel(): List<Event> {
    return this.map { dto ->
        Event(
            id = dto.id,
            name = dto.title,
            description = dto.description,
            imagePath = dto.thumbnailDto.path,
            imageExtension = dto.thumbnailDto.extension,
            comicsCount = dto.comicsSummaryDto.available,
            seriesCount = dto.seriesSummaryDto.available,
            charactersCount = dto.characterSummaryDto.available,
            detailLink = dto.urlDtos.find { it.type == "detail" }?.url,
            previousEventName = dto.previousDto?.name,
            previousEventResource = dto.previousDto?.resourceURI,
            nextEventName = dto.nextDto?.name,
            nextEventResource = dto.nextDto?.resourceURI
        )
    }
}