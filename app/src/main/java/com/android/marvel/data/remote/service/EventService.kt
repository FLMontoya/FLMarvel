package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.data.dto.comic.ComicResponseDto
import com.android.marvel.data.dto.event.EventResponseDto
import com.android.marvel.data.dto.serie.SerieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventService {

    @GET("events")
    suspend fun getEvents(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): EventResponseDto

    @GET("events/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): EventResponseDto

    @GET("events/{eventId}/characters")
    suspend fun getEventCharacters(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): CharacterResponseDto

    @GET("events/{eventId}/comics")
    suspend fun getEventComic(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): ComicResponseDto

    @GET("events/{eventId}/series")
    suspend fun getEventSerie(
        @Path("eventId") eventId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): SerieResponseDto



}