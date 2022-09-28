package com.android.marvel.iu.character


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.android.marvel.R
import com.android.marvel.databinding.FragmentCharacterBinding
import com.android.marvel.iu.character.adapter.CharacterAdapter
import com.android.marvel.iu.character.detail.CharacterDetailFragment
import com.android.marvel.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharacterViewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.title = "Marvel"

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
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
                                    characterViewModel.filterFlow.value = text
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

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        init()
    }

    private fun init() {

        context?.let {
            val characterAdapter = CharacterAdapter(object : CharacterListerner {
                override fun onClick(character: Character) {
                    navigateToCharacterDetailFragment(character)
                }
            })
            characterAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    characterRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                }
            }
            val characterLayoutManager = GridLayoutManager(it, 2)
            binding.characterRecyclerView.apply {
                layoutManager = characterLayoutManager
                adapter = characterAdapter

            }

            characterViewModel.getCharacters().observe(viewLifecycleOwner, Observer {pagingData ->
                characterAdapter.submitData(lifecycle, pagingData)
            })
        }
    }

    private fun navigateToCharacterDetailFragment(character: Character) {
        activity?.let {
            val characterDetailFragment = CharacterDetailFragment(character.id)
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, characterDetailFragment)
                .addToBackStack(CharacterDetailFragment::class.java.name)
                .commit()
        }
    }

    interface CharacterListerner {

        fun onClick(character: Character)

    }

}