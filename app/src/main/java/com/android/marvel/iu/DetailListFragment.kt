package com.android.marvel.iu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvel.R
import com.android.marvel.databinding.FragmentDetailListBinding
import com.android.marvel.iu.character.detail.CharacterDetailFragment
import com.android.marvel.iu.comic.ComicDetailFragment
import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType

abstract class DetailListFragment(private val detailItemType: DetailItemType) :
    Fragment(R.layout.fragment_detail_list) {

    private var _binding: FragmentDetailListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailListBinding.bind(view)

    }

    abstract fun getDetailListLiveDataPaging(): LiveData<PagingData<DetailItem>>?

    override fun onResume() {
        super.onResume()
        loadDetailItemList()

    }

    private fun loadDetailItemList() {
        binding.titleTextView.text = detailItemType.getTitle()
        context?.let {
            val detailListAdapter = DetailListAdapter(object : DetailItemListerner {
                override fun onClick(detailItem: DetailItem) {
                    navigateToDetailItem(detailItem)
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


    private fun navigateToDetailItem(detailItem: DetailItem) {

        when (detailItem.detailItemType) {
            DetailItemType.COMIC -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.flContainer, ComicDetailFragment(detailItem.id))
                    ?.addToBackStack(ComicDetailFragment::class.java.name)?.commit()
            }
            DetailItemType.SERIE -> {

            }
            DetailItemType.EVENT -> {

            }
            DetailItemType.CHARACTER -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.flContainer, CharacterDetailFragment(detailItem.id))
                    ?.addToBackStack(CharacterDetailFragment::class.java.name)?.commit()
            }
        }
    }

    interface DetailItemListerner {

        fun onClick(detailItem: DetailItem)

    }
}