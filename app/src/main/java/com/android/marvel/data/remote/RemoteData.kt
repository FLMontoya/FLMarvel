package com.android.marvel.data.remote

import com.android.marvel.data.dto.character.toCharacterModel
import com.android.marvel.data.remote.service.CharacterService
import com.android.marvel.data.remote.service.MarvelService
import com.android.marvel.data.dto.model.Character
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val marvelService: MarvelService) : RemoteDataSource {

    override suspend fun requestCharacters(page: Int, size: Int): List<Character> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacters(page, size)
            .data.characters.toCharacterModel()
    }
}