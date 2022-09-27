package com.android.marvel.data.remote

import com.android.marvel.data.dto.character.toCharacterModel
import com.android.marvel.data.dto.comic.toDetailItem

import com.android.marvel.data.dto.event.toDetailItem

import com.android.marvel.data.dto.serie.toDetailItem
import com.android.marvel.data.remote.service.CharacterService
import com.android.marvel.data.remote.service.MarvelService
import com.android.marvel.model.Character

import com.android.marvel.model.DetailItem

import javax.inject.Inject

class RemoteData @Inject constructor(
    private val marvelService: MarvelService) : RemoteDataSource {

    override suspend fun requestCharacters(page: Int, size: Int, query: String?): List<Character> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return if(query.isNullOrEmpty()) {
            characterService.getCharacters(page, size)
                .dataDto.results.toCharacterModel()
        } else {
            characterService.searchCharacter(query, page, size)
                .dataDto.results.toCharacterModel()
        }
    }

    override suspend fun requestCharacterComic(characterId: String, page: Int, size: Int): List<DetailItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterComics(characterId, page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestCharacterSerie(characterId: String, page: Int, size: Int): List<DetailItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterSeries(characterId, page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestCharacterEvent(characterId: String, page: Int, size: Int): List<DetailItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterEvents(characterId, page, size)
            .dataDto.results.toDetailItem()
    }


}