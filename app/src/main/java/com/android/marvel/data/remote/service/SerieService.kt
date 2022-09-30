package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.data.dto.comic.ComicResponseDto
import com.android.marvel.data.dto.event.EventResponseDto
import com.android.marvel.data.dto.serie.SerieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieService {

    @GET("series")
    suspend fun getSeries(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): SerieResponseDto

    @GET("series/{seriesId}")
    suspend fun getSerie(
        @Path("seriesId") seriesId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): SerieResponseDto

    @GET("series/{seriesId}/characters")
    suspend fun getSerieCharacters(
        @Path("seriesId") seriesId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): CharacterResponseDto

    @GET("series/{seriesId}/comics")
    suspend fun getSerieComics(
        @Path("seriesId") seriesId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): ComicResponseDto

    @GET("series/{seriesId}/events")
    suspend fun getSerieEvents(
        @Path("seriesId") seriesId: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): EventResponseDto



}