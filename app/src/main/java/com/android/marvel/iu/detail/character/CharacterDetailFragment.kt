package com.android.marvel.iu.detail.character


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.marvel.iu.detail.DetailListFragment
import com.android.marvel.iu.detail.MarvelDetailFragment
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment(private val characterId: Int) : MarvelDetailFragment() {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels({ this })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterDetailViewModel.setCharacterId(characterId)
        characterDetailViewModel.getCharacter().observe(viewLifecycleOwner, Observer { character ->
            loadData(character)
        })
    }

    override fun getDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment {
        return CharacterDetailListFragment(marvelItemType)
    }
}

class CharacterDetailListFragment(private val marvelItemType: MarvelItemType) :
    DetailListFragment(marvelItemType) {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels({ requireParentFragment() })

    override fun getDetailListLiveDataPaging(): LiveData<PagingData<MarvelItem>>? {
        return when (marvelItemType) {
            MarvelItemType.COMIC -> characterDetailViewModel.characterComics
            MarvelItemType.SERIE -> characterDetailViewModel.characterSeries
            MarvelItemType.EVENT -> characterDetailViewModel.characterEvents
            MarvelItemType.CHARACTER -> null
        }
    }
}
