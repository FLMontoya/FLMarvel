package com.android.marvel.iu.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    val filterFlow = MutableStateFlow<String>("")

    fun getCharacters(): LiveData<PagingData<Character>> {
        return filterFlow.flatMapLatest { filter ->
            dataRepository.searchCharacters(filter)
        }.cachedIn(viewModelScope).asLiveData()
    }
}
