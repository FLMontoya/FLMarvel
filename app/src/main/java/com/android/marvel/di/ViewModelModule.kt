package com.android.marvel.di

import com.android.marvel.data.remote.RemoteData
import com.android.marvel.data.repository.DataRepository
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
    fun dataRepositoryProvider(remoteData: RemoteData) = DataRepository(remoteData)


}