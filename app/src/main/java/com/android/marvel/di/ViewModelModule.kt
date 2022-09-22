package com.android.marvel.di

import com.android.marvel.data.datasource.RemoteDataSource
import com.android.marvel.data.datasource.impl.RemoteDataSourceImpl
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.data.repository.impl.DataRepositoryImpl
import com.android.marvel.data.service.MarvelService
import com.android.marvel.domain.CharactersPagingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun getCharactersPagingUseCase(dataRepository: DataRepositoryImpl) =
        CharactersPagingUseCase(dataRepository)

}