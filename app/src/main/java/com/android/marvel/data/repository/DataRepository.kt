package com.android.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.marvel.data.paging.CharacterPaging
import com.android.marvel.data.paging.ComicPaging
import com.android.marvel.data.paging.EventPaging
import com.android.marvel.data.paging.SeriePaging
import com.android.marvel.data.remote.RemoteData
import com.android.marvel.model.Character
import com.android.marvel.model.DetailItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteData: RemoteData
) : DataRepositorySource {

    override fun searchCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPaging(remoteData, query) }
        ).flow
    }

    override fun getCharacterComics(characterId: String): Flow<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { ComicPaging(remoteData, characterId) }
        ).flow
    }

    override fun getCharacterSeries(characterId: String): Flow<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { SeriePaging(remoteData, characterId) }
        ).flow
    }

    override fun getCharacterEvents(characterId: String): Flow<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
            pagingSourceFactory = { EventPaging(remoteData, characterId) }
        ).flow
    }
}