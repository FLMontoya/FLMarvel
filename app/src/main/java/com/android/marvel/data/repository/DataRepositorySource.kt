package com.android.marvel.data.repository

import com.android.marvel.data.dto.model.Character
import dagger.Provides
import io.reactivex.Single

interface DataRepositorySource {

    suspend fun requestCharacters(page: Int, size: Int): List<Character>

}