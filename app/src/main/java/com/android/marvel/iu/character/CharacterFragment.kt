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
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvel.R
import com.android.marvel.databinding.FragmentCharacterBinding
import com.android.marvel.data.dto.model.Character
import com.android.marvel.iu.character.adapter.CharacterAdapter

import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private val mDisposable = CompositeDisposable()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharacterViewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.action_search -> {
                        val searchView = menuItem.actionView as SearchView

                        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(p0: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(p0: String?): Boolean {
                                //adapter.getFilter().filter(newText);
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
            val characterLayoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
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
        /*activity?.let {
            val argument = Bundle()
            argument.putParcelable(CharacterModel::class.java.name, characterModel)
            val characterDetailFragment = CharacterDetailFragment()
            characterDetailFragment.arguments = argument
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, characterDetailFragment)
                .addToBackStack(CharacterDetailFragment::class.java.name)
                .commit()
        }*/
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {


        // Inflate the options menu from XML
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //adapter.getFilter().filter(newText);
                return false
            }
        })

        binding.toolbar.addView(searchView)
    }*/

    interface CharacterListerner {

        fun onClick(character: Character)

    }

}