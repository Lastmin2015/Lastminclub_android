package ru.vsibi.presentation.screens.saved

import ru.vsibi.presentation.screens.tours.main.TourModel

sealed class SavedViewState {
    class Loaded(val data : List<TourModel>) : SavedViewState()
}
