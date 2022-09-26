package com.android.marvel.data.remote

import com.android.marvel.data.dto.model.Character
import io.reactivex.Single

interface RemoteDataSource {
    suspend fun requestCharacters(page: Int, size: Int): List<Character>
}