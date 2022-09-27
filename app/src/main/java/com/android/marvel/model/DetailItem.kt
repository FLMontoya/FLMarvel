package com.android.marvel.model

data class DetailItem(
    val id: Int,
    val name: String,
    val image: String,
    val detailItemType: DetailItemType) {
}

enum class DetailItemType(private val title: String) {
    COMIC("COMIC"), SERIE("SERIE"), EVENT("EVENT"), CHARACTER("CHARACTER");

    fun getTitle() = title
}