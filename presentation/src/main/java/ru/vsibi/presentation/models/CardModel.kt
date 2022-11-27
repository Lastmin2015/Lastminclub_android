package ru.vsibi.presentation.models

class CardModel(
    val title : String,
    val number : String,
    val iconRes : Int
) {
    var isChecked = false
}