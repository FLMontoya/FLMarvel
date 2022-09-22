package com.android.marvel.data.service

import com.android.marvel.data.dto.character.CharacterResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharacterService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): Single<CharacterResponseDto>



}