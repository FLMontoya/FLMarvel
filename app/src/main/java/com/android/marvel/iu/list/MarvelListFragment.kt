package com.android.marvel.iu.list


import android.os.Bundle
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.android.marvel.R
import com.android.marvel.databinding.FragmentMarvelListBinding

import com.android.marvel.iu.detail.character.detail.CharacterDetailFragment
import com.android.marvel.model.Character
import com.android.marvel.model.MarvelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class MarvelListFragment : Fragment(R.layout.fragment_marvel_list), MenuProvider {

    private var _binding: FragmentMarvelListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMarvelListBinding.bind(view)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        loadMarvelList()
    }

    private fun loadMarvelList() {
        context?.let {
            val marvelListAdapter = MarvelListAdapter(object : MarvelItemListener {
                override fun onClick(marvelItem: MarvelItem) {

                }
            }).apply {
                addLoadStateListener { loadState ->
                    binding.apply {
                        progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                        characterRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                    }
                }
            }

            binding.characterRecyclerView.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = marvelListAdapter
                setHasFixedSize(true)
            }

            getMarvelItemLiveDataPaging().observe(viewLifecycleOwner, Observer {pagingData ->
                marvelListAdapter.submitData(lifecycle, pagingData)
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

    interface MarvelItemListener {

        fun onClick(marvelItem: MarvelItem)

    }

    abstract fun getMarvelItemLiveDataPaging(): LiveData<PagingData<MarvelItem>>

    abstract fun navigateToMarvelItemDetail(marvelItem: MarvelItem)

}