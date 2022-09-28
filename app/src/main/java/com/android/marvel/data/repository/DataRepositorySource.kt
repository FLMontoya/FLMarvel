package com.android.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import com.android.marvel.model.Event
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun getCharacter(characterId: Int): Character
    fun searchCharacters(query: String): Flow<PagingData<Character>>
    fun getCharacterComics(characterId: Int): LiveData<PagingData<DetailItem>>
    fun getCharacterSeries(characterId: Int): LiveData<PagingData<DetailItem>>
    fun getCharacterEvents(characterId: Int): LiveData<PagingData<DetailItem>>

    suspend fun getComic(comicId: Int): Comic
    fun getComicCharacters(comicId: Int): LiveData<PagingData<DetailItem>>
    fun getComicEvents(comicId: Int): LiveData<PagingData<DetailItem>>

    suspend fun getEvent(eventId: Int): Event
    fun getEventCharacters(eventId: Int): LiveData<PagingData<DetailItem>>
    fun getEventComic(eventId: Int): LiveData<PagingData<DetailItem>>

}