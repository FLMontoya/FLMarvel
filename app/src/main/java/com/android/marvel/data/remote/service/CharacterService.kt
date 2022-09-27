package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.data.dto.comic.ComicResponseDto
import com.android.marvel.data.dto.event.EventResponseDto
import com.android.marvel.data.dto.serie.SerieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): CharacterResponseDto

    @GET("characters")
    suspend fun searchCharacter(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): CharacterResponseDto

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponseDto

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ) : SerieResponseDto

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ) : EventResponseDto

}