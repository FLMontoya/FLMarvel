package com.android.marvel.iu.character.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.android.marvel.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private var characterIdLiveData = MutableLiveData<Int>()
    fun setCharacterId(characterId: Int) {
        characterIdLiveData.value = characterId
    }

    private val character = characterIdLiveData.switchMap {
        liveData { emit(dataRepository.getCharacter(it)) }
    }

    val characterComics = characterIdLiveData.switchMap {
        dataRepository.getCharacterComics(it)
    }

    val characterSeries = characterIdLiveData.switchMap {
        dataRepository.getCharacterSeries(it)
    }

    val characterEvents = characterIdLiveData.switchMap {
        dataRepository.getCharacterEvents(it)
    }

    fun getCharacter() = character

}

