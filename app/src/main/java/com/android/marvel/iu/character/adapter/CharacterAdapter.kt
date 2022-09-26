package com.android.marvel.iu.character.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.marvel.databinding.RowCharacterBinding
import com.android.marvel.data.dto.model.Character
import com.android.marvel.iu.character.CharacterFragment



class CharacterAdapter(private val characterListerner: CharacterFragment.CharacterListerner) :
    PagingDataAdapter<Character, CharacterViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rowCharacterBinding = RowCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(rowCharacterBinding, characterListerner)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }
}