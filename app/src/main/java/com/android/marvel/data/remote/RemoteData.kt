package com.android.marvel.data.remote

import com.android.marvel.data.dto.character.toCharacterModel
import com.android.marvel.data.dto.character.toDetailItem
import com.android.marvel.data.dto.comic.toDetailItem
import com.android.marvel.data.dto.comic.toModel
import com.android.marvel.data.dto.event.toDetailItem
import com.android.marvel.data.dto.event.toModel
import com.android.marvel.data.dto.serie.toDetailItem
import com.android.marvel.data.dto.serie.toModel
import com.android.marvel.data.remote.service.CharacterService
import com.android.marvel.data.remote.service.ComicService
import com.android.marvel.data.remote.service.EventService
import com.android.marvel.data.remote.service.MarvelService
import com.android.marvel.data.remote.service.SerieService
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.Serie
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val marvelService: MarvelService
) : RemoteDataSource {

    override suspend fun requestCharacter(characterId: Int): Character {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterById(characterId.toString(), 0, 1)
            .dataDto.results.toCharacterModel().first()
    }

    /**
     * CHARACTERS
     */
    override suspend fun requestCharacters(page: Int, size: Int, query: String?): List<MarvelItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return if (query.isNullOrEmpty()) {
            characterService.getCharacters(page, size)
                .dataDto.results.toDetailItem()
        } else {
            characterService.searchCharacter(query, page, size)
                .dataDto.results.toDetailItem()
        }
    }

    override suspend fun requestCharacterComic(characterId: Int, page: Int, size: Int): List<MarvelItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterComics(characterId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestCharacterSerie(characterId: Int, page: Int, size: Int): List<MarvelItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterSeries(characterId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestCharacterEvent(characterId: Int, page: Int, size: Int): List<MarvelItem> {
        val characterService = marvelService.createService(CharacterService::class.java)
        return characterService.getCharacterEvents(characterId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    /**
     * COMICS
     */
    override suspend fun requestComic(comicId: Int): Comic {
        val comicService = marvelService.createService(ComicService::class.java)
        return comicService.getComic(comicId = comicId.toString(), 0, 1)
            .dataDto.results.toModel().first()
    }

    override suspend fun requestComics(page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(ComicService::class.java)
            .getComics(page, size).dataDto.results.toDetailItem()
    }

    override suspend fun requestComicCharacter(comicId: Int, page: Int, size: Int): List<MarvelItem> {
        val comicService = marvelService.createService(ComicService::class.java)
        return comicService.getComicCharacters(comicId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestComicEvent(comicId: Int, page: Int, size: Int): List<MarvelItem> {
        val comicService = marvelService.createService(ComicService::class.java)
        return comicService.getComicEvents(comicId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }


    /**
     * SERIE
     */
    override suspend fun requestSerie(seriesId: Int): Serie {
        return marvelService.createService(SerieService::class.java)
            .getSerie(seriesId.toString(), 0, 1)
            .dataDto.results.toModel().first()
    }

    override suspend fun requestSeries(page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(SerieService::class.java)
            .getSeries(page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestSerieCharacter(seriesId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(SerieService::class.java)
            .getSerieCharacters(seriesId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestSerieComic(seriesId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(SerieService::class.java)
            .getSerieComics(seriesId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestSerieEvent(seriesId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(SerieService::class.java)
            .getSerieEvents(seriesId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    /**
     * EVENTS
     */
    override suspend fun requestEvents(page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(EventService::class.java)
            .getEvents(page, size).dataDto.results.toDetailItem()
    }

    override suspend fun requestEvent(eventId: Int): Event {
        return marvelService.createService(EventService::class.java)
            .getEvent(eventId.toString(), 0, 1)
            .dataDto.results.toModel().first()
    }

    override suspend fun requestEventCharacter(eventId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(EventService::class.java)
            .getEventCharacters(eventId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestEventComic(eventId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(EventService::class.java)
            .getEventComic(eventId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

    override suspend fun requestEventSerie(eventId: Int, page: Int, size: Int): List<MarvelItem> {
        return marvelService.createService(EventService::class.java)
            .getEventSerie(eventId.toString(), page, size)
            .dataDto.results.toDetailItem()
    }

}