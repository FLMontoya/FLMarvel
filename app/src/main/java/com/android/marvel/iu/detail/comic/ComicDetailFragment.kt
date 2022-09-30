package com.android.marvel.iu.detail.comic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.marvel.R
import com.android.marvel.databinding.ExtraDataComicBinding
import com.android.marvel.databinding.FragmentComicDetailBinding
import com.android.marvel.iu.detail.DetailListFragment
import com.android.marvel.iu.detail.MarvelDetailFragment
import com.android.marvel.model.Comic
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.MarvelModel
import com.android.marvel.utils.ImageLoadingUtils
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment(private val comicId: Int) : MarvelDetailFragment() {

    private val comicDetailViewModel: ComicDetailViewModel by viewModels({ this })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comicDetailViewModel.setComicId(comicId)
        comicDetailViewModel.getComic().observe(viewLifecycleOwner, Observer { comic ->
            loadData(comic)
        })
    }

    override fun getDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment {
        return ComicDetailListFragment(marvelItemType)
    }

    override fun initMarvelModelInfo(marvelModel: MarvelModel) {
        super.initMarvelModelInfo(marvelModel)
        val extraBinding = ExtraDataComicBinding.inflate(LayoutInflater.from(context))
        binding.flContainerExtraData.addView(extraBinding.root)
        val comic = marvelModel as Comic
        extraBinding.apply {
            comicNumPagesTextView.text = "Paginas: " + comic.numPages
            comicPriceTextView.text = "Precio: " + comic.price
        }
    }
}

class ComicDetailListFragment(private val marvelItemType: MarvelItemType) :
    DetailListFragment(marvelItemType) {

    private val comicDetailViewModel: ComicDetailViewModel by viewModels({ requireParentFragment() })

    override fun getDetailListLiveDataPaging(): LiveData<PagingData<MarvelItem>>? {
        return when (marvelItemType) {
            MarvelItemType.COMIC -> null
            MarvelItemType.SERIE -> null
            MarvelItemType.EVENT -> comicDetailViewModel.comicEvents
            MarvelItemType.CHARACTER -> comicDetailViewModel.comicCharacters
        }
    }
}