package com.android.marvel.iu.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.flowable
import com.android.marvel.data.dto.model.Character
import com.android.marvel.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    fun getCharacters(): LiveData<PagingData<Character>> {
        return Pager(PagingConfig(20)){
            CharacterPaging(dataRepository)
        }.liveData.cachedIn(this)
    }
}
