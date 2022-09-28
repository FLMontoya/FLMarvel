package com.android.marvel.iu.comic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.marvel.R
import com.android.marvel.databinding.FragmentComicDetailBinding
import com.android.marvel.iu.DetailListFragment
import com.android.marvel.iu.character.detail.CharacterDetailListFragment
import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment(private val comicId: Int) : Fragment(R.layout.fragment_comic_detail), MenuProvider {

    private val comicDetailViewModel: ComicDetailViewModel by viewModels({ this })
    private var _binding: FragmentComicDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComicDetailBinding.bind(view)

        comicDetailViewModel.setComicId(comicId)
        comicDetailViewModel.getComic().observe(viewLifecycleOwner, Observer { comic ->
            loadComicInfo(comic)
            loadComicDetailList(comic)
            loadToolbar()
            loadMenu()
        })
    }



    private fun loadMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun loadToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun loadComicInfo(comic: Comic) {
        binding.descriptionTextView.text = comic.description
        binding.collapser.title = comic.name
        binding.toolbar.title = comic.name
        Picasso.get().load(comic.getDetail()).into(binding.imageBackground)
    }

    private fun loadComicDetailList(comic: Comic) {
        loadComicCharacterList(comic)
        loadComicEventList(comic)
    }

    private fun loadComicEventList(comic: Comic) {
        if (comic.eventsCount > 0) {
            childFragmentManager.apply {
                var fragment = findFragmentByTag(DetailItemType.EVENT.getTitle())
                if (fragment == null) {
                    fragment = ComicDetailListFragment(DetailItemType.EVENT)
                }
                beginTransaction().replace(binding.flContainerEvents.id, fragment, DetailItemType.EVENT.getTitle())
                    .commit()
            }
        }
    }

    private fun loadComicCharacterList(comic: Comic) {
        if (comic.characterCount > 0) {
            childFragmentManager.apply {
                var fragment = findFragmentByTag(DetailItemType.CHARACTER.getTitle())
                if (fragment == null) {
                    fragment = ComicDetailListFragment(DetailItemType.CHARACTER)
                }
                beginTransaction().replace(binding.flContainerCharacters.id, fragment, DetailItemType.CHARACTER.getTitle())
                    .commit()
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_details -> {
                comicDetailViewModel.getComic().observe(viewLifecycleOwner, Observer { comic ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(comic.detailLink)
                    startActivity(intent)

                })
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val menuItem = menu.findItem(R.id.action_details)
        comicDetailViewModel.getComic().observe(viewLifecycleOwner, Observer { comic ->
            when {
                comic.detailLink.isNullOrEmpty() -> {
                    menuItem.isVisible = false
                }
                else -> {
                    menuItem.isVisible = true
                }
            }

        })
    }
}

class ComicDetailListFragment(private val detailItemType: DetailItemType) :
    DetailListFragment(detailItemType) {

    private val comicDetailViewModel: ComicDetailViewModel by viewModels({ requireParentFragment() })


    override fun getDetailListLiveDataPaging(): LiveData<PagingData<DetailItem>>? {
        return when (detailItemType) {
            DetailItemType.COMIC -> null
            DetailItemType.SERIE -> null
            DetailItemType.EVENT -> comicDetailViewModel.comicEvents
            DetailItemType.CHARACTER -> comicDetailViewModel.comicCharacters
        }
    }
}