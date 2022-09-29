package com.android.marvel.iu.list.comic


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.R
import com.android.marvel.iu.detail.comic.ComicDetailFragment
import com.android.marvel.iu.list.MarvelListFragment
import com.android.marvel.model.MarvelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicListFragment : MarvelListFragment() {

    private val comicListViewModel: ComicListViewModel by viewModels<ComicListViewModel>()

    override fun getMarvelItemLiveDataPaging(): LiveData<PagingData<MarvelItem>> {
        return comicListViewModel.getComics()
    }

    override fun navigateToMarvelItemDetail(marvelItem: MarvelItem) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flContainer, ComicDetailFragment(marvelItem.id), ComicDetailFragment::class.java.name)
            ?.addToBackStack(ComicDetailFragment::class.java.name)?.commit()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }


}