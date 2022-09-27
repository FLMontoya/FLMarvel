package com.android.marvel.iu.character.detail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.android.marvel.R
import com.android.marvel.databinding.FragmentCharacterDetailBinding

import com.android.marvel.model.Character
import com.android.marvel.model.DetailItemType
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels({ this })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            val marvelCharacterModel = it.getParcelable<Character>(Character::class.java.name)
            marvelCharacterModel?.let { model ->
                characterDetailViewModel.saveMarvelCharacterModel(model)
            }
        }
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        loadCharacterInfo()
        loadCharacterDetailList()
    }

    private fun loadCharacterDetailList() {
        characterDetailViewModel.getMarvelCharacterModel().let { model ->
            childFragmentManager.beginTransaction().apply {
                if (model.comicsCount > 0) replace(
                    binding.flContainerComics.id,
                    CharacterDetailListFragment(DetailItemType.COMIC)
                )
                if (model.seriesCount > 0) replace(
                    binding.flContainerSeries.id,
                    CharacterDetailListFragment(DetailItemType.SERIE)
                )
                if (model.eventsCount > 0) replace(
                    binding.flContainerEvents.id,
                    CharacterDetailListFragment(DetailItemType.EVENT)
                )
            }.commitNow()
        }
    }


    private fun loadCharacterInfo() {
        characterDetailViewModel.getMarvelCharacterModel().let { model ->
            binding.characterDescription.text = model.description
            binding.collapser.title = model.name
            binding.toolbar.title = model.name
            Picasso.get().load(model.getDetail()).into(binding.characterBackground)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.character_detail_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_details -> {
                characterDetailViewModel.getMarvelCharacterModel().detailLink.let { link ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(link)
                    startActivity(intent)
                    true
                }
            }
            else -> {
                false
            }
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val menuItem = menu.findItem(R.id.action_details)
        characterDetailViewModel.getMarvelCharacterModel().detailLink.let { link ->
            when {
                link.isNullOrEmpty() -> {
                    menuItem.isVisible = false
                }
                else -> {
                    menuItem.isVisible = true
                }
            }
        }
    }

}