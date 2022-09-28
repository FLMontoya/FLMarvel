package com.android.marvel.data.dto.comic

import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType
import com.google.gson.annotations.SerializedName

data class ComicDataDto(

    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<ComicDto>
)

fun Collection<ComicDto>.toDetailItem(): List<DetailItem> {
    return this.map { dto ->
        DetailItem(
            dto.id,
            dto.title,
            dto.thumbnailDto.getPortraitUncanny(),
            DetailItemType.COMIC
        )
    }
}

fun Collection<ComicDto>.toModel(): List<Comic> {
    return this.map { dto ->
        Comic(dto.id,
            dto.title,
            description = if (dto.description.isNullOrEmpty()) {
                "Descripción no disponible"
            } else {
                dto.description
            },
            dto.thumbnailDto.path,
            dto.thumbnailDto.extension,
            0,
            dto.eventsSummaryDto.available,
            dto.seriesSummaryDto.available,
            dto.characterSummaryDto.available,
            dto.urlDtos.find { it.type == "detail" }?.url
        )
    }
}