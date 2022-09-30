package com.android.marvel.iu.detail.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.android.marvel.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EventDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private var eventIdLiveData = MutableLiveData<Int>()
    fun setEventId(eventId: Int) {
        eventIdLiveData.value = eventId
    }

    private val event = eventIdLiveData.switchMap {
        liveData { emit(dataRepository.getEvent(it)) }
    }

    fun getEvent() = event

    val charactersList = eventIdLiveData.switchMap {
        dataRepository.getEventCharacters(it)
    }

    val comicsList = eventIdLiveData.switchMap {
        dataRepository.getEventComic(it)
    }

    val seriesList = eventIdLiveData.switchMap {
        dataRepository.getEventSerie(it)
    }

}