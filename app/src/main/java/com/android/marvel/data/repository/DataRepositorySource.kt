package com.android.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.Serie
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun getCharacter(characterId: Int): Character
    fun searchCharacters(query: String): Flow<PagingData<MarvelItem>>
    fun getCharacterComics(characterId: Int): LiveData<PagingData<MarvelItem>>
    fun getCharacterSeries(characterId: Int): LiveData<PagingData<MarvelItem>>
    fun getCharacterEvents(characterId: Int): LiveData<PagingData<MarvelItem>>

    suspend fun getComic(comicId: Int): Comic
    fun getComics(): LiveData<PagingData<MarvelItem>>
    fun getComicCharacters(comicId: Int): LiveData<PagingData<MarvelItem>>
    fun getComicEvents(comicId: Int): LiveData<PagingData<MarvelItem>>

    suspend fun getEvent(eventId: Int): Event
    fun getEvents(): LiveData<PagingData<MarvelItem>>
    fun getEventCharacters(eventId: Int): LiveData<PagingData<MarvelItem>>
    fun getEventComic(eventId: Int): LiveData<PagingData<MarvelItem>>
    fun getEventSerie(eventId: Int): LiveData<PagingData<MarvelItem>>

    suspend fun getSerie(serieId: Int): Serie
    fun getSeries(): LiveData<PagingData<MarvelItem>>
    fun getSerieCharacters(serieId: Int): LiveData<PagingData<MarvelItem>>
    fun getSerieComic(serieId: Int): LiveData<PagingData<MarvelItem>>
    fun getSerieEvent(serieId: Int): LiveData<PagingData<MarvelItem>>


}