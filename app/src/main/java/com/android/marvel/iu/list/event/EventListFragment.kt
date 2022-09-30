package com.android.marvel.iu.list.event


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.marvel.R
import com.android.marvel.iu.detail.comic.ComicDetailFragment
import com.android.marvel.iu.detail.event.EventDetailFragment
import com.android.marvel.iu.list.MarvelListFragment
import com.android.marvel.iu.list.comic.ComicListViewModel
import com.android.marvel.model.MarvelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : MarvelListFragment() {

    private val eventListViewModel: EventListViewModel by viewModels()

    override fun getMarvelItemLiveDataPaging(): LiveData<PagingData<MarvelItem>> {
        return eventListViewModel.getEvents()
    }

    override fun navigateToMarvelItemDetail(marvelItem: MarvelItem) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flContainer, EventDetailFragment(marvelItem.id), EventDetailFragment::class.java.name)
            ?.addToBackStack(EventDetailFragment::class.java.name)?.commit()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }


}