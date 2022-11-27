package ru.vsibi.presentation.screens.tours.info.flights.main

import ru.vsibi.presentation.base.BaseViewModel

class FlightsListViewModel : BaseViewModel<FlightsListState, FlightsListAction, FlightsListEvent>() {

    private var flights = FlightsMockFactory.getFlights()
    override fun obtainEvent(viewEvent: FlightsListEvent) {
        when(viewEvent){
            is FlightsListEvent.FetchData -> fetchHotels()
        }
    }

    private fun fetchHotels() {
        viewState = FlightsListState.Loaded(flights)
    }

    fun getFligths() = flights

}