package com.android.marvel.iu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.marvel.R
import com.android.marvel.databinding.FragmentMarvelMainBinding
import com.android.marvel.iu.list.character.CharacterListFragment
import com.android.marvel.iu.list.comic.ComicListFragment
import com.android.marvel.iu.list.event.EventListFragment
import com.android.marvel.iu.list.serie.SerieListFragment
import com.android.marvel.model.MarvelItemType

class MarvelMainFragment : Fragment(R.layout.fragment_marvel_main) {

    private var _binding: FragmentMarvelMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var marvelPageAdapter: MarvelPageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMarvelMainBinding.bind(view)

        marvelPageAdapter = MarvelPageAdapter(childFragmentManager)
        binding.apply {
            viewPager.adapter = marvelPageAdapter
            tabLayout.setupWithViewPager(viewPager)

            (activity as AppCompatActivity).apply {
                setSupportActionBar(binding.toolbar)
                supportActionBar?.setDisplayShowTitleEnabled(true)
                supportActionBar?.title = "Marvel"
            }
        }
    }

    inner class MarvelPageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int {
            return MarvelItemType.values().size
        }

        override fun getItem(position: Int): Fragment {
            return when(MarvelItemType.values()[position]){
                MarvelItemType.COMIC -> ComicListFragment()
                MarvelItemType.SERIE -> SerieListFragment()
                MarvelItemType.EVENT -> EventListFragment()
                MarvelItemType.CHARACTER -> CharacterListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return MarvelItemType.values()[position].getTitle()
        }
    }
}