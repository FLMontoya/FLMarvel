package com.android.marvel.data.datasource

import com.android.marvel.data.dto.character.CharacterResponseDto
import io.reactivex.Single

interface RemoteDataSource {
    fun requestCharacters(page: Int, size: Int): Single<CharacterResponseDto>
}