package com.android.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.Event
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun getCharacter(characterId: Int): Character
    fun searchCharacters(query: String): Flow<PagingData<MarvelItem>>
    fun getCharacterComics(characterId: Int): LiveData<PagingData<MarvelItem>>
    fun getCharacterSeries(characterId: Int): LiveData<PagingData<MarvelItem>>
    fun getCharacterEvents(characterId: Int): LiveData<PagingData<MarvelItem>>

    suspend fun getComic(comicId: Int): Comic
    fun getComicCharacters(comicId: Int): LiveData<PagingData<MarvelItem>>
    fun getComicEvents(comicId: Int): LiveData<PagingData<MarvelItem>>

    suspend fun getEvent(eventId: Int): Event
    fun getEventCharacters(eventId: Int): LiveData<PagingData<MarvelItem>>
    fun getEventComic(eventId: Int): LiveData<PagingData<MarvelItem>>

    fun searchComics(query: String): Flow<PagingData<MarvelItem>>
}