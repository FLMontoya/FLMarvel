package com.android.marvel.data.paging

import com.android.marvel.data.remote.RemoteData
import com.android.marvel.model.DetailItem

class SeriePaging (
    private val remoteData: RemoteData,
    private val characterId: String
) : MarvelPagingSource<DetailItem>() {
    override suspend fun getResponse(params: LoadParams<Int>): List<DetailItem> {
        return remoteData.requestCharacterSerie(page = params.key ?: 0, size = params.loadSize, characterId = characterId)
    }
}