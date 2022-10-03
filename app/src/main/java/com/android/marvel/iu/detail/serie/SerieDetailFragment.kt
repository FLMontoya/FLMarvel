package com.android.marvel.iu.detail.serie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.marvel.databinding.ExtraDataSerieBinding
import com.android.marvel.iu.detail.DetailListFragment
import com.android.marvel.iu.detail.MarvelDetailFragment
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.MarvelModel
import com.android.marvel.model.Serie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerieDetailFragment(private val serieId: Int) : MarvelDetailFragment() {

    private val viewModel: SerieDetailViewModel by viewModels({ this })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setSerieId(serieId)
        viewModel.getSerie().observe(viewLifecycleOwner, Observer { serie ->
            loadData(serie)
        })
    }

    override fun initMarvelModelInfo(marvelModel: MarvelModel) {
        super.initMarvelModelInfo(marvelModel)
        val extraDataBinding = ExtraDataSerieBinding.inflate(LayoutInflater.from(context))
        binding.flContainerExtraData.removeAllViews()
        binding.flContainerExtraData.addView(extraDataBinding.root)
        /**
         * Eventos anterior y siguiente.
         */
        (marvelModel as Serie).let { serie ->
            extraDataBinding.apply {
                serie.previousSerieName?.let {
                    previousSerieName.text = serie.previousSerieName
                    serie.previousSerieResource?.let { serieResource ->
                        previousSerie.isVisible = true
                        previousSerie.setOnClickListener {
                            val eventId = serieResource.split("/").last()
                            eventId.let { it1 -> viewModel.setSerieId(it1.toInt()) }
                        }
                    }
                }
                serie.nextSerieName?.let {
                    nextSerieName.text = serie.nextSerieName
                    serie.nextSerieResource?.let { serieResource ->
                        nextSerie.isVisible = true
                        nextSerie.setOnClickListener {
                            val eventId = serieResource.split("/").last()
                            eventId.let { it1 -> viewModel.setSerieId(it1.toInt()) }
                        }
                    }
                }
            }
        }
    }

    override fun getDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment {
        return SerieDetailListFragment(marvelItemType)
    }
}

class SerieDetailListFragment(private val marvelItemType: MarvelItemType) :
    DetailListFragment(marvelItemType) {

    private val viewModel: SerieDetailViewModel by viewModels({ requireParentFragment() })

    override fun getDetailListLiveDataPaging(): LiveData<PagingData<MarvelItem>>? {
        return when (marvelItemType) {
            MarvelItemType.COMIC -> viewModel.comicsList
            MarvelItemType.SERIE -> null
            MarvelItemType.EVENT -> viewModel.eventList
            MarvelItemType.CHARACTER -> viewModel.charactersList
        }
    }
}
