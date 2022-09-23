package com.android.marvel.data.repository

import com.android.marvel.domain.model.Character
import io.reactivex.Single

interface DataRepository {

    fun requestCharacters(page: Int, size: Int): Single<List<Character>>

}