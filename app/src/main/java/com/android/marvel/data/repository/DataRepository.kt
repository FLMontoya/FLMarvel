package com.android.marvel.data.repository

import com.android.marvel.data.remote.RemoteData

import com.android.marvel.data.dto.model.Character
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteData: RemoteData
) : DataRepositorySource {

    override suspend fun requestCharacters(page: Int, size: Int): List<Character> {
        return remoteData.requestCharacters(page, size)

    }
}