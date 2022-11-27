package ru.vsibi.presentation.screens.tours.info.flights.main

sealed class FlightsListEvent {
    class Default() : FlightsListEvent()
    class FetchData() : FlightsListEvent()
}
