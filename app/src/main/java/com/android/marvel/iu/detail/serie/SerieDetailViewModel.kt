package com.android.marvel.iu.detail.serie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.android.marvel.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SerieDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private var serieIdLiveData = MutableLiveData<Int>()
    fun setSerieId(serieId: Int) {
        serieIdLiveData.value = serieId
    }

    private val serie = serieIdLiveData.switchMap {
        liveData { emit(dataRepository.getSerie(it)) }
    }

    fun getSerie() = serie

    val charactersList = serieIdLiveData.switchMap {
        dataRepository.getSerieCharacters(it)
    }

    val comicsList = serieIdLiveData.switchMap {
        dataRepository.getSerieComic(it)
    }

    val eventList = serieIdLiveData.switchMap {
        dataRepository.getSerieEvent(it)
    }



}