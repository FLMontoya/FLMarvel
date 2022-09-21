package com.android.marvel.data.repository.impl

import com.android.marvel.data.datasource.impl.RemoteDataSourceImpl
import com.android.marvel.data.dto.character.CharacterResponse
import com.android.marvel.data.repository.CharacterRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSourceImpl: RemoteDataSourceImpl) : CharacterRepository {

    override suspend fun requestCharacters(page: Int, size: Int): Single<CharacterResponse> {
        return characterDataSourceImpl.requestCharacters(page, size)
    }
}