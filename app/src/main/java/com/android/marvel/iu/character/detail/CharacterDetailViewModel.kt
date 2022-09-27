package com.android.marvel.iu.character.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.marvel.data.repository.DataRepository
import com.android.marvel.model.Character
import com.android.marvel.model.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private lateinit var character: Character


    fun saveMarvelCharacterModel(character: Character) {
        this.character = character
    }

    fun getMarvelCharacterModel() = character

    fun getCharacterComics(): LiveData<PagingData<DetailItem>> {
        return dataRepository.getCharacterComics(character.id.toString()).asLiveData().cachedIn(viewModelScope)
    }

    fun getCharacterSeries(): LiveData<PagingData<DetailItem>> {
        return dataRepository.getCharacterSeries(character.id.toString()).asLiveData().cachedIn(viewModelScope)
    }

    fun getCharacterEvents(): LiveData<PagingData<DetailItem>> {
        return dataRepository.getCharacterEvents(character.id.toString()).asLiveData().cachedIn(viewModelScope)
    }

    /*
    fun getComics(): Flowable<PagingData<ComicModel>> {
        return comicInteractor.getComics(characterModel)
    }

    fun getSeries(): Flowable<PagingData<SerieModel>> {
        return serieInteractor.getSeries(characterModel)
    }

    fun getEvents(): Flowable<PagingData<EventModel>> {
        return eventInteractor.getEvents(characterModel)
    }
    */


}

