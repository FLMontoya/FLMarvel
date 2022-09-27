package com.android.marvel.data.paging

import com.android.marvel.data.remote.RemoteData
import com.android.marvel.model.Character

class CharacterPaging (
    private val remoteData: RemoteData,
    private val query: String
) : MarvelPagingSource<Character>() {

    override suspend fun getResponse(params: LoadParams<Int>): List<Character> {
        return remoteData.requestCharacters(page = params.key ?: 0, size = params.loadSize, query = query)
    }
}