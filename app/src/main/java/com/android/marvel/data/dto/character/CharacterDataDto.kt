package com.android.marvel.data.dto.character


import com.android.marvel.model.Character
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.google.gson.annotations.SerializedName

data class CharacterDataDto(

    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<CharacterDto>
)

fun Collection<CharacterDto>.toCharacterModel(): List<Character> {
    return this.map { dto ->
        Character(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            imagePath = dto.thumbnailDto.path,
            imageExtension = dto.thumbnailDto.extension,
            comicsCount = dto.comicsSummaryDto.available,
            eventsCount = dto.eventsSummaryDto.available,
            seriesCount = dto.seriesSummaryDto.available,
            detailLink = dto.urlDtoList.find { it.type == "detail" }?.url
        )
    }
}

fun Collection<CharacterDto>.toDetailItem(): List<MarvelItem> {
    return this.map { dto ->
        MarvelItem(
            id = dto.id,
            name = dto.name,
            image = dto.thumbnailDto.getPortrait(),
            marvelItemType = MarvelItemType.CHARACTER
        )
    }
}

