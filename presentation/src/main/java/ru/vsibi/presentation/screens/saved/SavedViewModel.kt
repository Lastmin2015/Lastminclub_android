package ru.vsibi.presentation.screens.saved

import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.screens.tours.main.HotelsMockFactory

class SavedViewModel : BaseViewModel<SavedViewState, SavedAction, SavedEvent>() {

    override fun obtainEvent(viewEvent: SavedEvent) {
        when(viewEvent){
            is SavedEvent.Default -> fetchHotels()
        }
    }

    private fun fetchHotels() {
        viewState = SavedViewState.Loaded(HotelsMockFactory.getMockFavoriteHotels())
    }

}