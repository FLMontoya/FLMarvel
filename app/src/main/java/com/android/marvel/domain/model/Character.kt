package com.android.marvel.domain.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String
)
