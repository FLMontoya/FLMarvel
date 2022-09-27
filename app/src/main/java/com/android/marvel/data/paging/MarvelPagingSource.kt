package com.android.marvel.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

abstract class MarvelPagingSource<T : Any> : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            toLoadResult(getResponse(params), params.key ?: 0)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    abstract suspend fun getResponse(params: LoadParams<Int>): List<T>

    private fun toLoadResult(list: List<T>, page: Int): LoadResult<Int, T> {
        return LoadResult.Page(
            data = list,
            prevKey = if (page == 0) null else page - list.size,
            nextKey = if (list.isEmpty()) null else page + list.size
        )
    }


}