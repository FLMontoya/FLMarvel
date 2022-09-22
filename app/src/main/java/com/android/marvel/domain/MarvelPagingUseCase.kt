package com.android.marvel.domain

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource

abstract class MarvelPagingUseCase<T : Any> : RxPagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    /*fun toLoadResult(list: List<T>, offset: Int, count: Int, total: Int): LoadResult<Int, T> {
        return LoadResult.Page(
            data = list,
            prevKey = if (offset == 0) null else offset - count,
            nextKey = if (offset >= total) null else offset + count
        )
    }*/

    fun toLoadResult(list: List<T>, page: Int): LoadResult<Int, T> {
        return LoadResult.Page(
            data = list,
            prevKey = if (page == 0) null else page - list.size,
            nextKey = page + list.size
        )
    }


}