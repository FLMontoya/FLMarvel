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
import com.android.marvel.model.MarvelItem
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.background
import com.example.awesomedialog.body
import com.example.awesomedialog.icon
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
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
                    navigateToMarvelItemDetail(marvelItem)
                }
            }).apply {
                addLoadStateListener { loadState ->
                    binding.apply {
                        loadState.refresh
                        progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                        characterRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                        if (loadState.source.refresh is LoadState.Error) {
                            activity?.let {
                                AwesomeDialog.build(it)
                                    .title(getString(R.string.error))
                                    .background(R.color.marvel_dark)
                                    .body(getString(R.string.error_connection))
                                    .icon(com.google.android.material.R.drawable.mtrl_ic_error)
                                    .onNegative(getString(R.string.salir)) {
                                        it.finish()
                                    }
                                    .onPositive(getString(R.string.reintentar)) {
                                        retry()
                                    }
                            }
                        }
                    }
                }
            }

            binding.characterRecyclerView.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = marvelListAdapter
                setHasFixedSize(true)
            }

            getMarvelItemLiveDataPaging().observe(viewLifecycleOwner, Observer { pagingData ->
                marvelListAdapter.submitData(lifecycle, pagingData)
            })
        }
    }

    interface MarvelItemListener {

        fun onClick(marvelItem: MarvelItem)

    }

    abstract fun getMarvelItemLiveDataPaging(): LiveData<PagingData<MarvelItem>>

    abstract fun navigateToMarvelItemDetail(marvelItem: MarvelItem)

}