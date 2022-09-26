package com.android.marvel.iu.character


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvel.databinding.FragmentCharacterBinding
import com.android.marvel.data.dto.model.Character
import com.android.marvel.iu.character.adapter.CharacterAdapter

import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private val mDisposable = CompositeDisposable()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharacterViewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {

        context?.let {
            val characterAdapter = CharacterAdapter(object : CharacterListerner {
                override fun onClick(character: Character) {
                    navigateToCharacterDetailFragment(character)
                }
            })
            val characterLayoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            binding.characterRecyclerView.apply {
                layoutManager = characterLayoutManager
                adapter = characterAdapter
            }
            /*mDisposable.add(characterViewModel.getCharacters().subscribe { pagingData ->
                characterAdapter.submitData(lifecycle, pagingData)
            })*/
            characterViewModel.getCharacters().observe(viewLifecycleOwner, Observer {pagingData ->
                characterAdapter.submitData(lifecycle, pagingData)
            })
        }
    }

    private fun navigateToCharacterDetailFragment(character: Character) {
        /*activity?.let {
            val argument = Bundle()
            argument.putParcelable(CharacterModel::class.java.name, characterModel)
            val characterDetailFragment = CharacterDetailFragment()
            characterDetailFragment.arguments = argument
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, characterDetailFragment)
                .addToBackStack(CharacterDetailFragment::class.java.name)
                .commit()
        }*/
    }

    interface CharacterListerner {

        fun onClick(character: Character)

    }

}