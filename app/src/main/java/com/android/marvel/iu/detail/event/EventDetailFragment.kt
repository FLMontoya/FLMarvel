package com.android.marvel.iu.detail.event


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.marvel.databinding.ExtraDataEventBinding
import com.android.marvel.iu.detail.DetailListFragment
import com.android.marvel.iu.detail.MarvelDetailFragment
import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.MarvelModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment(private val eventId: Int) : MarvelDetailFragment() {

    private val viewModel: EventDetailViewModel by viewModels({ this })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setEventId(eventId)
        viewModel.getEvent().observe(viewLifecycleOwner, Observer { event ->
            loadData(event)
        })
    }

    override fun getDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment {
        return EventDetailListFragment(marvelItemType)
    }

    override fun initMarvelModelInfo(marvelModel: MarvelModel) {
        super.initMarvelModelInfo(marvelModel)
        val extraDataBinding = ExtraDataEventBinding.inflate(LayoutInflater.from(context))
        binding.flContainerExtraData.removeAllViews()
        binding.flContainerExtraData.addView(extraDataBinding.root)
        /**
         * Eventos anterior y siguiente.
         */
        (marvelModel as Event).let { event ->
            extraDataBinding.apply {
                event.previousEventName?.let {
                    previousEventName.text = event.previousEventName
                    event.previousEventResource?.let { eventResource ->
                        previousEvent.isVisible = true
                        previousEvent.setOnClickListener {
                            val eventId = eventResource.split("/").last()
                            eventId.let { it1 -> viewModel.setEventId(it1.toInt()) }
                        }
                    }
                }
                event.nextEventName?.let {
                    nextEventName.text = event.nextEventName
                    event.nextEventResource?.let { eventResource ->
                        nextEvent.isVisible = true
                        nextEvent.setOnClickListener {
                            val eventId = eventResource.split("/").last()
                            eventId.let { it1 -> viewModel.setEventId(it1.toInt()) }
                        }
                    }
                }
            }
        }
    }
}

class EventDetailListFragment(private val marvelItemType: MarvelItemType) :
    DetailListFragment(marvelItemType) {

    private val eventDetailViewModel: EventDetailViewModel by viewModels({ requireParentFragment() })

    override fun getDetailListLiveDataPaging(): LiveData<PagingData<MarvelItem>>? {
        return when (marvelItemType) {
            MarvelItemType.COMIC -> eventDetailViewModel.comicsList
            MarvelItemType.SERIE -> eventDetailViewModel.seriesList
            MarvelItemType.EVENT -> null
            MarvelItemType.CHARACTER -> eventDetailViewModel.charactersList
        }
    }
}
