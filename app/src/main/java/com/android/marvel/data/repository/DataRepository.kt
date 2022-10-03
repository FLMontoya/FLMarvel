package com.android.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.liveData
import com.android.marvel.data.paging.MarvelPagingSource
import com.android.marvel.data.paging.PagingConfigUtil
import com.android.marvel.data.remote.RemoteData
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.Serie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteData: RemoteData
) : DataRepositorySource {

    override suspend fun getCharacter(characterId: Int): Character {
        return remoteData.requestCharacter(characterId)
    }

    override fun searchCharacters(query: String): Flow<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfig(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestCharacters(
                            page = params.key ?: 0,
                            size = params.loadSize,
                            query = query
                        )
                    }
                }
            }
        ).flow
    }

    override fun getCharacterComics(characterId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestCharacterComic(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getCharacterSeries(characterId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestCharacterSerie(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getCharacterEvents(characterId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestCharacterEvent(characterId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override suspend fun getComic(comicId: Int): Comic {
        return remoteData.requestComic(comicId)
    }

    override fun getComics(): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfig(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestComics(
                            page = params.key ?: 0,
                            size = params.loadSize,
                        )
                    }
                }
            }
        ).liveData
    }

    override fun getComicCharacters(comicId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestComicCharacter(comicId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getComicEvents(comicId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestComicEvent(comicId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override suspend fun getEvent(eventId: Int): Event {
        return remoteData.requestEvent(eventId)
    }

    override fun getEvents(): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfig(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestEvents(
                            page = params.key ?: 0,
                            size = params.loadSize,
                        )
                    }
                }
            }
        ).liveData
    }


    override fun getEventCharacters(eventId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestEventCharacter(eventId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getEventComic(eventId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestEventComic(eventId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getEventSerie(eventId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestEventSerie(eventId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override suspend fun getSerie(serieId: Int): Serie {
        return remoteData.requestSerie(serieId)
    }

    override fun getSeries(): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfig(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestSeries(
                            page = params.key ?: 0,
                            size = params.loadSize,
                        )
                    }
                }
            }
        ).liveData
    }

    override fun getSerieCharacters(serieId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestSerieCharacter(serieId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getSerieComic(serieId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestSerieComic(serieId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }

    override fun getSerieEvent(serieId: Int): LiveData<PagingData<MarvelItem>> {
        return Pager(
            config = PagingConfigUtil.getPageConfigDetail(),
            pagingSourceFactory = {
                object : MarvelPagingSource<MarvelItem>() {
                    override suspend fun getResponse(params: LoadParams<Int>): List<MarvelItem> {
                        return remoteData.requestSerieEvent(serieId, params.key ?: 0, params.loadSize)
                    }
                }
            }
        ).liveData
    }


}