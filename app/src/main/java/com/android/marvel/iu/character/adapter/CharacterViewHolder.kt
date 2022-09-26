package com.android.marvel.iu.character.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.databinding.RowCharacterBinding
import com.android.marvel.data.dto.model.Character
import com.android.marvel.iu.character.CharacterFragment
import com.squareup.picasso.Picasso


class CharacterViewHolder(
    private val rowCharacterBinding: RowCharacterBinding,
    private val characterListerner: CharacterFragment.CharacterListerner
) :
    RecyclerView.ViewHolder(rowCharacterBinding.root) {

    fun bind(character: Character) {
        rowCharacterBinding.apply {
            characterNameTextView.text = character.name.uppercase()
            Picasso.get().load(character.getLandscape()).into(characterImageView)
            root.setOnClickListener {
                characterListerner.onClick(character)
            }
        }



    }


}