package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: ThumbnailDto,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("comics") val comics: ComicsDto,
    @SerializedName("series") val series: SeriesDto,
    @SerializedName("stories") val stories: StoriesDto,
    @SerializedName("events") val events: EventsDto,
    @SerializedName("urls") val urls: List<UrlDto>
)