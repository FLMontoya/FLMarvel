package com.android.marvel.data.datasource

import com.android.marvel.data.dto.character.CharacterResponse
import io.reactivex.Single

internal interface RemoteDataSource {
    suspend fun requestCharacters(page: Int, size: Int): Single<CharacterResponse>
}