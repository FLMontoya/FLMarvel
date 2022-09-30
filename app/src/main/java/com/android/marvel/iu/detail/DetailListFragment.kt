package com.android.marvel.iu.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvel.R
import com.android.marvel.databinding.FragmentDetailListBinding
import com.android.marvel.iu.detail.character.CharacterDetailFragment
import com.android.marvel.iu.detail.comic.ComicDetailFragment
import com.android.marvel.iu.detail.event.EventDetailFragment
import com.android.marvel.iu.detail.serie.SerieDetailFragment
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType

abstract class DetailListFragment(private val marvelItemType: MarvelItemType) :
    Fragment(R.layout.fragment_detail_list) {

    private var _binding: FragmentDetailListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailListBinding.bind(view)
    }

    abstract fun getDetailListLiveDataPaging(): LiveData<PagingData<MarvelItem>>?

    override fun onResume() {
        super.onResume()
        loadDetailItemList()
    }

    private fun loadDetailItemList() {
        binding.titleTextView.text = marvelItemType.getTitle()
        context?.let {
            val detailListAdapter = DetailListAdapter(object : DetailItemListerner {
                override fun onClick(marvelItem: MarvelItem) {
                    navigateToDetailItem(marvelItem)
                }
            })

            binding.rvItemList.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = detailListAdapter
            }
            getDetailListLiveDataPaging()?.let { liveData ->
                liveData.observe(viewLifecycleOwner, Observer { pagingData ->
                    detailListAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                })
            }
        }
    }


    private fun navigateToDetailItem(marvelItem: MarvelItem) {
        val fragment = when (marvelItem.marvelItemType) {
            MarvelItemType.CHARACTER -> CharacterDetailFragment(marvelItem.id)
            MarvelItemType.COMIC -> ComicDetailFragment(marvelItem.id)
            MarvelItemType.SERIE -> SerieDetailFragment(marvelItem.id)
            MarvelItemType.EVENT -> EventDetailFragment(marvelItem.id)
        }
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.flContainer,
            fragment,
            fragment::class.java.name
        )?.addToBackStack(fragment::class.java.name)?.commit()

    }

    interface DetailItemListerner {
        fun onClick(marvelItem: MarvelItem)
    }
}