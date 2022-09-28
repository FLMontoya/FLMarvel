package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.data.dto.comic.ComicResponseDto
import com.android.marvel.data.dto.event.EventResponseDto
import com.android.marvel.data.dto.serie.SerieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventService {

    @GET("events/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): EventResponseDto

    @GET("events/{eventId}/characters")
    suspend fun getEventCharacters(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): CharacterResponseDto

    @GET("events/{eventId}/comics")
    suspend fun getEventComic(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponseDto



}