package com.android.marvel.iu.character

import androidx.paging.PagingState
import com.android.marvel.data.dto.model.Character
import com.android.marvel.data.repository.DataRepositorySource
import com.android.marvel.iu.common.MarvelPagingSource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterPaging @Inject constructor(
    private val dataRepositorySource: DataRepositorySource
) : MarvelPagingSource<Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: 0

        return try {
            val response = dataRepositorySource.requestCharacters(page = offset, size = params.loadSize)
            toLoadResult(response, offset)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}