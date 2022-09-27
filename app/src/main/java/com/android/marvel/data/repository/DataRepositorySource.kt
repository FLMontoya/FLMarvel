package com.android.marvel.data.repository

import androidx.paging.PagingData
import com.android.marvel.model.Character
import com.android.marvel.model.DetailItem
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    fun searchCharacters(query: String): Flow<PagingData<Character>>

    fun getCharacterComics(characterId: String): Flow<PagingData<DetailItem>>
    fun getCharacterSeries(characterId: String): Flow<PagingData<DetailItem>>
    fun getCharacterEvents(characterId: String): Flow<PagingData<DetailItem>>
}