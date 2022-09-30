package com.android.marvel.iu.list.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.model.MarvelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    fun getEvents(): LiveData<PagingData<MarvelItem>> {
        return dataRepository.getEvents().cachedIn(viewModelScope)
    }
}
