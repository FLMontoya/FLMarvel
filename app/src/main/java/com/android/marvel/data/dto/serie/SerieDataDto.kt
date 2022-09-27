package com.android.marvel.data.dto.serie

import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType
import com.google.gson.annotations.SerializedName


data class SerieDataDto(

    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("count")
    val count: Int,
    @SerializedName("results") var results: ArrayList<SerieDao> = arrayListOf()

)

fun Collection<SerieDao>.toDetailItem(): List<DetailItem> {
    return this.map { dto ->
        DetailItem(
            dto.id,
            dto.title,
            dto.thumbnailDto.getPortraitUncanny(),
            DetailItemType.SERIE
        )
    }
}