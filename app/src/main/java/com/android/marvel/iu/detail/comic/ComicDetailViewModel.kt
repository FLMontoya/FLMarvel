package com.android.marvel.iu.detail.comic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.android.marvel.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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