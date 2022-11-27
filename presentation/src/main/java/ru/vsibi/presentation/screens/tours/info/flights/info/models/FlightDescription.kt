package ru.vsibi.presentation.screens.tours.info.flights.info.models

class FlightDescription(
    val duration : String,
    val companyName : String,
    val `class` : String
) : FlightInfo{
    var isChecked = false
}