package com.android.marvel.data.remote.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): CharacterResponseDto



}