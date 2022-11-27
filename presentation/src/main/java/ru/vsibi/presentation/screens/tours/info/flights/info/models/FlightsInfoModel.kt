package ru.vsibi.presentation.screens.tours.info.flights.info.models

import ru.vsibi.presentation.screens.tours.info.flights.info.FlightsInfoChildFragment
import ru.vsibi.presentation.screens.tours.info.flights.main.FlightsModel

class FlightsInfoModel(
    val fragment: FlightsInfoChildFragment = FlightsInfoChildFragment(),
    val flight : FlightsModel
) {
    constructor(flight : FlightsModel) : this(FlightsInfoChildFragment(), flight)
    constructor() : this(FlightsInfoChildFragment(), FlightsModel())
}