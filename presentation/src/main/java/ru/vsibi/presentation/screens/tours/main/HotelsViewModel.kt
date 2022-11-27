package ru.vsibi.presentation.screens.tours.main

import ru.vsibi.presentation.base.BaseViewModel

class HotelsViewModel : BaseViewModel<HotelsViewState, HotelsAction, HotelsEvent>() {


    override fun obtainEvent(viewEvent: HotelsEvent) {
        when(viewEvent){
            is HotelsEvent.Default -> fetchHotels()
        }
    }

    private fun fetchHotels() {
        viewState = HotelsViewState.Loaded(HotelsMockFactory.getMockHotels())
    }

}