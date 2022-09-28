package com.android.marvel.data.paging

import androidx.paging.PagingConfig

object PagingConfigUtil {

    fun getPageConfig() = PagingConfig(
        pageSize = 50,
        enablePlaceholders = false,
        prefetchDistance = 10,
        initialLoadSize = 80
    )

    fun getPageConfigDetail() = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
        prefetchDistance = 5,
        initialLoadSize = 20
    )
}