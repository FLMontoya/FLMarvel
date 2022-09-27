package com.android.marvel.iu.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvel.databinding.FragmentDetailListBinding
import com.android.marvel.model.DetailItem
import com.android.marvel.model.DetailItemType

class CharacterDetailListFragment(private val detailItemType: DetailItemType) : Fragment() {

    private var _binding: FragmentDetailListBinding? = null
    private val binding get() = _binding!!
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels<CharacterDetailViewModel>({ requireParentFragment() })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun getTitleResource(): String {
        return detailItemType.getTitle()
    }

    private fun getLiveDataPaging(): LiveData<PagingData<DetailItem>> {
        return when(detailItemType){
            DetailItemType.COMIC -> characterDetailViewModel.getCharacterComics()
            DetailItemType.EVENT -> characterDetailViewModel.getCharacterEvents()
            else -> {
                characterDetailViewModel.getCharacterSeries()
            }
        }
    }

    private fun init() {
        binding.titleTextView.text = getTitleResource()
        context?.let {
            val infoAdapter = DetailListAdapter()
            binding.rvItemList.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = infoAdapter
            }
            getLiveDataPaging().observe(viewLifecycleOwner, Observer { pagingData ->
                infoAdapter.submitData(lifecycle, pagingData)
            })


        }
    }
}