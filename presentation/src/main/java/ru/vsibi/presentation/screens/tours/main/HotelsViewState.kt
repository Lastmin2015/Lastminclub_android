package ru.vsibi.presentation.screens.tours.main

sealed class HotelsViewState {
    class Loaded(val data : List<TourModel>) : HotelsViewState()
}