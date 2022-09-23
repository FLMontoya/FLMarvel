package com.android.marvel.data.repository.impl

import com.android.marvel.data.datasource.impl.RemoteDataSourceImpl

import com.android.marvel.data.dto.character.toCharacterModel
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.domain.model.Character
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) : DataRepository {

    override fun requestCharacters(page: Int, size: Int): Single<List<Character>> {
        return remoteDataSource.requestCharacters(page, size)
            .map {
                it.data.characters.toCharacterModel()
            }
    }
}