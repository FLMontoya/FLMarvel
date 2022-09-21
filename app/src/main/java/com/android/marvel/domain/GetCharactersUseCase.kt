package com.android.marvel.domain

import com.android.marvel.data.repository.impl.CharacterRepositoryImpl
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepositoryImpl
) : PagingS{
}