package com.android.marvel.data.repository

import com.android.marvel.data.dto.character.CharacterResponse
import io.reactivex.Single

internal interface CharacterRepository {

    suspend fun requestCharacters(page: Int, size: Int) : Single<CharacterResponse>

}