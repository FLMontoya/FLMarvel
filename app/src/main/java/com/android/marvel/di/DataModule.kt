package com.android.marvel.di

import com.android.marvel.data.datasource.RemoteDataSource
import com.android.marvel.data.datasource.impl.RemoteDataSourceImpl
import com.android.marvel.data.repository.impl.DataRepositoryImpl
import com.android.marvel.data.service.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun dataRepositoryProvider(remoteDataSource: RemoteDataSourceImpl) = DataRepositoryImpl(remoteDataSource)

    @Provides
    fun remoteDataSourceProvider(marvelService: MarvelService) = RemoteDataSourceImpl(marvelService)

    @Provides
    fun serviceProvider() = MarvelService()
}