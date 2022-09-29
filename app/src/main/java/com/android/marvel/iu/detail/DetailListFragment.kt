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
import com.android.marvel.iu.detail.character.detail.CharacterDetailFragment
import com.android.marvel.iu.detail.comic.ComicDetailFragment
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

        when (marvelItem.marvelItemType) {
            MarvelItemType.COMIC -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.flContainer, ComicDetailFragment(marvelItem.id))
                    ?.addToBackStack(ComicDetailFragment::class.java.name)?.commit()
            }
            MarvelItemType.SERIE -> {

            }
            MarvelItemType.EVENT -> {

            }
            MarvelItemType.CHARACTER -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.flContainer, CharacterDetailFragment(marvelItem.id))
                    ?.addToBackStack(CharacterDetailFragment::class.java.name)?.commit()
            }
        }
    }

    interface DetailItemListerner {

        fun onClick(marvelItem: MarvelItem)

    }
}