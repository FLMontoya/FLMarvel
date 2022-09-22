package com.android.marvel.presentation.character.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.databinding.RowCharacterBinding
import com.android.marvel.domain.model.CharacterModel
import com.android.marvel.presentation.character.CharacterFragment


class CharacterViewHolder(
    private val rowCharacterBinding: RowCharacterBinding,
    private val characterListerner: CharacterFragment.CharacterListerner
) :
    RecyclerView.ViewHolder(rowCharacterBinding.root) {

    fun bind(characterModel: CharacterModel) {
        rowCharacterBinding.apply {
            characterNameTextView.text = characterModel.name
        }


    }


}