package com.android.marvel.data.dto.character


import com.android.marvel.domain.model.Character
import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val characters: List<CharacterDto>
)

fun Collection<CharacterDto>.toCharacterModel(): List<Character> {
    return this.map { characterDto ->
        Character(
            characterDto.id,
            characterDto.name,
            characterDto.description,
            characterDto.thumbnail.path,
            characterDto.thumbnail.extension
        )
    }
}

