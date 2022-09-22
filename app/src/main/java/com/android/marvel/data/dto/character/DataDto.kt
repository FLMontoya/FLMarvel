package com.android.marvel.data.dto.character


import com.android.marvel.domain.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val characters: List<CharacterDto>
)

fun Collection<CharacterDto>.toCharacterModel(): List<CharacterModel> {
    return this.map { characterDto ->
        CharacterModel(
            characterDto.id,
            characterDto.name,
            characterDto.description
        )
    }
}

