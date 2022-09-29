package com.android.marvel.data.dto.event

import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.Event
import com.google.gson.annotations.SerializedName


data class EventDataDto(

    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("count")
    val count: Int,
    @SerializedName("results") val results: List<EventDto>
)

fun Collection<EventDto>.toDetailItem(): List<MarvelItem> {
    return this.map { dto ->
        MarvelItem(
            dto.id,
            dto.title,
            dto.thumbnailDto.getPortraitUncanny(),
            MarvelItemType.EVENT
        )
    }
}

fun Collection<EventDto>.toModel(): List<Event> {
    return this.map { dto ->
        Event(
            dto.id,
            dto.title,
            description = if (dto.description.isNullOrEmpty()) {
                "Descripción no disponible"
            } else {
                dto.description
            },
            dto.thumbnailDto.path,
            dto.thumbnailDto.extension,
            dto.comicsSummaryDto.available,
            0,
            dto.seriesSummaryDto.available,
            dto.characterSummaryDto.available,
            dto.urlDtos.find { it.type == "detail" }?.url
        )
    }
}