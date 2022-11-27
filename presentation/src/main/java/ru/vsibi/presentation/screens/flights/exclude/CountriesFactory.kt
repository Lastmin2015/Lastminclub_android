package ru.vsibi.presentation.screens.flights.exclude

import ru.vsibi.presentation.models.CountryModel

object CountriesFactory {
    fun getSelectedCountries(): List<CountryModel> {
        val list = mutableListOf<CountryModel>()
        list.add(CountryModel("UK", true))
        list.add(CountryModel("Russia", true))
        list.add(CountryModel("Ukraine", true))
        return list
    }
}