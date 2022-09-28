package com.android.marvel.iu.character.detail


import android.content.Context
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
import com.android.marvel.databinding.FragmentCharacterDetailBinding
import com.android.marvel.iu.DetailListFragment

import com.android.marvel.model.Character
import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class CharacterDetailFragment(private val characterId: Int) : Fragment(R.layout.fragment_character_detail),
    MenuProvider {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels({ this })
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        characterDetailViewModel.setCharacterId(characterId)
        characterDetailViewModel.getCharacter().observe(viewLifecycleOwner, Observer { character ->
            loadCharacterInfo(character)
            loadCharacterDetailList(character)
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
            activity?.supportFragmentManager?.popBackStackImmediate()
        }
    }



    private fun loadCharacterDetailList(character: Character) {
        loadCharacterComicList(character)
        loadCharacterEventList(character)
        loadCharacterSerieList(character)
    }

    private fun loadCharacterSerieList(character: Character) {
        if(character.seriesCount > 0){
            childFragmentManager.apply {
                var fragment = findFragmentByTag(DetailItemType.SERIE.getTitle())
                if(fragment == null) {
                    fragment = CharacterDetailListFragment(DetailItemType.SERIE)
                }
                beginTransaction().replace(binding.flContainerSeries.id, fragment, DetailItemType.SERIE.getTitle()).commit()
            }
        }
    }

    private fun loadCharacterEventList(character: Character) {
        if(character.eventsCount > 0){
            childFragmentManager.apply {
                var fragment = findFragmentByTag(DetailItemType.EVENT.getTitle())
                if(fragment == null) {
                    fragment = CharacterDetailListFragment(DetailItemType.EVENT)
                }
                beginTransaction().replace(binding.flContainerEvents.id, fragment, DetailItemType.EVENT.getTitle()).commit()
            }
        }
    }

    private fun loadCharacterComicList(character: Character) {
        if(character.comicsCount > 0){
            childFragmentManager.apply {
                var fragment = findFragmentByTag(DetailItemType.COMIC.getTitle())
                if(fragment == null) {
                    fragment = CharacterDetailListFragment(DetailItemType.COMIC)
                }
                beginTransaction().replace(binding.flContainerComics.id, fragment, DetailItemType.COMIC.getTitle()).commit()
            }
        }
    }

    private fun loadCharacterInfo(character: Character) {
        binding.characterDescription.text = character.description
        binding.collapser.title = character.name
        binding.toolbar.title = character.name
        Picasso.get().load(character.getDetail()).into(binding.characterBackground)

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_details -> {
                characterDetailViewModel.getCharacter().observe(viewLifecycleOwner, Observer { character ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(character.detailLink)
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
        characterDetailViewModel.getCharacter().observe(viewLifecycleOwner, Observer { character ->
            when {
                character.detailLink.isNullOrEmpty() -> {
                    menuItem.isVisible = false
                }
                else -> {
                    menuItem.isVisible = true
                }
            }

        })
    }
}

class CharacterDetailListFragment(private val detailItemType: DetailItemType) :
    DetailListFragment(detailItemType) {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels({ requireParentFragment() })

    override fun getDetailListLiveDataPaging(): LiveData<PagingData<DetailItem>>? {
        return when (detailItemType) {
            DetailItemType.COMIC -> characterDetailViewModel.characterComics
            DetailItemType.SERIE -> characterDetailViewModel.characterSeries
            DetailItemType.EVENT -> characterDetailViewModel.characterEvents
            DetailItemType.CHARACTER -> null
        }
    }
}
