package com.android.marvel.presentation.character.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.marvel.databinding.RowCharacterBinding
import com.android.marvel.domain.model.CharacterModel
import com.android.marvel.presentation.character.CharacterFragment



class CharacterAdapter(private val characterListerner: CharacterFragment.CharacterListerner) :
    PagingDataAdapter<CharacterModel, CharacterViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rowCharacterBinding = RowCharacterBinding.inflate(inflater)
        return CharacterViewHolder(rowCharacterBinding, characterListerner)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }
    }
}