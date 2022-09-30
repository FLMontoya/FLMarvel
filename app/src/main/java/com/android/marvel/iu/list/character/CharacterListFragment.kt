package com.android.marvel.iu.list.character


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.R
import com.android.marvel.iu.detail.character.CharacterDetailFragment
import com.android.marvel.iu.list.MarvelListFragment
import com.android.marvel.model.MarvelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : MarvelListFragment() {

    private val characterListViewModel: CharacterListViewModel by viewModels<CharacterListViewModel>()

    override fun getMarvelItemLiveDataPaging(): LiveData<PagingData<MarvelItem>> {
        return characterListViewModel.getCharacters()
    }

    override fun navigateToMarvelItemDetail(marvelItem: MarvelItem) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flContainer, CharacterDetailFragment(marvelItem.id), CharacterDetailFragment::class.java.name)
            ?.addToBackStack(CharacterDetailFragment::class.java.name)?.commit()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.character_list_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.action_search -> {
                val searchView = menuItem.actionView as SearchView

                searchView.imeOptions = EditorInfo.IME_ACTION_DONE
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(text: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(text: String?): Boolean {
                        text?.let {
                            characterListViewModel.filterFlow.value = text
                        }
                        return false
                    }
                })
                true
            }
            else -> {
                false
            }
        }
    }


}