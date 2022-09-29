package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.data.dto.comic.ComicResponseDto
import com.android.marvel.data.dto.event.EventResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicService {

    @GET("comics")
    suspend fun getComics(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): ComicResponseDto

    @GET("comics")
    suspend fun searchComics(
        @Query("titleStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponseDto

    @GET("comics/{comicId}")
    suspend fun getComic(
        @Path("comicId") comicId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponseDto

    @GET("comics/{comicId}/characters")
    suspend fun getComicCharacters(
        @Path("comicId") comicId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): CharacterResponseDto

    @GET("comics/{comicId}/events")
    suspend fun getComicEvents(
        @Path("comicId") comicId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): EventResponseDto

}