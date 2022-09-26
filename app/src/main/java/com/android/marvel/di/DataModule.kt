package com.android.marvel.di

import com.android.marvel.data.remote.RemoteData
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.data.remote.service.MarvelService
import com.android.marvel.iu.character.CharacterPaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    fun remoteDataSourceProvider(marvelService: MarvelService) = RemoteData(marvelService)

    @Provides
    fun serviceProvider() = MarvelService()

    @Provides
    fun characterPagingSourceProvider(dataRepository: DataRepository) =
        CharacterPaging(dataRepository)
}