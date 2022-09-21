package com.android.marvel.data.datasource.impl

import com.android.marvel.data.datasource.RemoteDataSource
import com.android.marvel.data.dto.character.CharacterResponse
import com.android.marvel.data.service.CharacterService
import com.android.marvel.data.service.MarvelService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val marvelService: MarvelService) : RemoteDataSource{

    override suspend fun requestCharacters(page: Int, size: Int): Single<CharacterResponse> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacters(page, size)
    }
}