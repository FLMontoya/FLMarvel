package com.android.marvel.iu.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private var comicIdLiveData = MutableLiveData<Int>()
    fun setComicId(comicId: Int){
        comicIdLiveData.value = comicId
    }

    private val comic = comicIdLiveData.switchMap {
        liveData { emit(dataRepository.getComic(it)) }
    }

    fun getComic() = comic

    val comicEvents = comicIdLiveData.switchMap {
        dataRepository.getComicEvents(it)
    }

    val comicCharacters = comicIdLiveData.switchMap {
        dataRepository.getComicCharacters(it)
    }

}