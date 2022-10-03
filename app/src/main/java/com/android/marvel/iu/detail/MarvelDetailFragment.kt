package com.android.marvel.iu.detail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.android.marvel.R
import com.android.marvel.databinding.FragmentMarvelDetailBinding
import com.android.marvel.model.MarvelItemType
import com.android.marvel.model.MarvelModel
import com.bumptech.glide.Glide

abstract class MarvelDetailFragment() : Fragment(R.layout.fragment_marvel_detail), MenuProvider {

    private var _binding: FragmentMarvelDetailBinding? = null
    val binding get() = _binding!!
    private var detailLink: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       _binding = FragmentMarvelDetailBinding.bind(view)
    }

    fun loadData(marvelModel: MarvelModel) {
        initMarvelModelInfo(marvelModel)
        initDetailList(marvelModel)
        initToolbar(binding.toolbar)
        initMenuHost()
    }

    open fun initMarvelModelInfo(marvelModel: MarvelModel) {
        detailLink = marvelModel.detailLink
        binding.apply {
            descriptionTextView.text = marvelModel.getFormatDescription()
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
            collapsingToolbarLayout.title = marvelModel.name
            toolbar.title = marvelModel.name
            Glide.with(root).load(marvelModel.getDetail()).centerInside().into(imageBackground)
        }
    }


    fun initToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    fun initMenuHost() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_details -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(detailLink)
                startActivity(intent)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val menuItem = menu.findItem(R.id.action_details)
        when {
            detailLink.isNullOrEmpty() -> {
                menuItem.isVisible = false
            }
            else -> {
                menuItem.isVisible = true
            }
        }
    }

    fun initDetailList(marvelModel: MarvelModel) {
        if(marvelModel.isComicsEnabled()) loadDetailList(MarvelItemType.COMIC)
        if(marvelModel.isCharactersEnabled()) loadDetailList(MarvelItemType.CHARACTER)
        if(marvelModel.isEventsEnabled()) loadDetailList(MarvelItemType.EVENT)
        if(marvelModel.isSeriesEnabled()) loadDetailList(MarvelItemType.SERIE)

    }

    private fun loadDetailList(marvelItemType: MarvelItemType) {
        childFragmentManager.beginTransaction()
            .replace(
                getDetailListContainerId(marvelItemType),
                findDetailListFragment(marvelItemType),
                marvelItemType.getTitle()
            )
            .commit()
    }

    private fun getDetailListContainerId(marvelItemType: MarvelItemType): Int {
        return when (marvelItemType) {
            MarvelItemType.CHARACTER -> binding.detailListItem.flContainerCharacters.id
            MarvelItemType.COMIC -> binding.detailListItem.flContainerComics.id
            MarvelItemType.SERIE -> binding.detailListItem.flContainerSeries.id
            MarvelItemType.EVENT -> binding.detailListItem.flContainerEvents.id
        }
    }

    private fun findDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment {
        var fragment = childFragmentManager.findFragmentByTag(marvelItemType.getTitle())
        if (fragment == null) {
            fragment = getDetailListFragment(marvelItemType)
        }
        return fragment as DetailListFragment
    }

    abstract fun getDetailListFragment(marvelItemType: MarvelItemType): DetailListFragment


}