package com.android.marvel.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.domain.CharactersPagingUseCase
import com.android.marvel.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val charactersPagingUseCase: CharactersPagingUseCase): ViewModel() {



    fun getCharacters(): Flowable<PagingData<CharacterModel>> {
        return Pager(PagingConfig(20)){
            charactersPagingUseCase
        }.flowable
    }

    override fun onCleared() {
        super.onCleared()
    }

}
