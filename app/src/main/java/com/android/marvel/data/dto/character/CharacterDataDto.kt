package com.android.marvel.data.dto.character


import com.android.marvel.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterDataDto(

    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("count")
    val count: Int,
    @field:SerializedName("results")
    val results: List<CharacterDto>
)

fun Collection<CharacterDto>.toCharacterModel(): List<Character> {
    return this.map { characterDto ->
        Character(
            characterDto.id,
            characterDto.name,
            characterDto.description.let {
                it.ifEmpty() { "Descripción no disponible" }
            },
            characterDto.thumbnailDto.path,
            characterDto.thumbnailDto.extension,
            characterDto.comicsSummaryDto.available,
            characterDto.eventsSummaryDto.available,
            characterDto.seriesSummaryDto.available,
            characterDto.urlDtoList.find { it.type == "detail" }?.url
        )
    }
}