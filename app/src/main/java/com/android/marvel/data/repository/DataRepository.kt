package com.android.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.liveData
import com.android.marvel.data.paging.CharacterPaging
import com.android.marvel.data.paging.MarvelPagingSource
import com.android.marvel.data.paging.PagingConfigUtil
import com.android.marvel.data.remote.RemoteData
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import com.android.marvel.model.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteData: RemoteData
) : DataRepositorySource {

    override suspend fun getCharacter(characterId: Int): Character {
        return remoteData.requestCharacter(characterId)
    }

    override fun searchCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfigUtil.getPageConfig(),
            pagingSourceFactory = { CharacterPaging(remoteData, query) }
        ).flow
    }

    override fun getCharacterComics(characterId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestCharacterComic(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getCharacterSeries(characterId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestCharacterSerie(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getCharacterEvents(characterId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestCharacterEvent(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override suspend fun getComic(comicId: Int): Comic {
        return remoteData.requestComic(comicId)
    }

    override fun getComicCharacters(comicId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestComicCharacter(comicId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getComicEvents(comicId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestComicEvent(comicId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override suspend fun getEvent(eventId: Int): Event {
        return remoteData.requestEvent(eventId)
    }

    override fun getEventCharacters(eventId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestEventCharacter(eventId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getEventComic(eventId: Int): LiveData<PagingData<DetailItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<DetailItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
                        return remoteData.requestEventComic(eventId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }


}