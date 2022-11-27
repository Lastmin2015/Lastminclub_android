package ru.vsibi.presentation.screens.tours.info.flights.main

import ru.vsibi.presentation.models.flight.FlightResponse

sealed class FlightsListState {
    class Loaded(val data : FlightResponse) : FlightsListState()
}
