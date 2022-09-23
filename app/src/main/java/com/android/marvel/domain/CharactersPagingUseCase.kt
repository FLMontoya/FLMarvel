package com.android.marvel.domain

import androidx.paging.PagingState
import com.android.marvel.data.repository.impl.DataRepositoryImpl
import com.android.marvel.domain.model.Character
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersPagingUseCase @Inject constructor(
    private val dataRepository: DataRepositoryImpl
) : MarvelPagingUseCase<Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Character>> {
        val offset = params.key ?: 0

        return dataRepository.requestCharacters(page = offset, size = params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it, offset)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

}