package ru.vsibi.presentation.screens.tours.info

import ru.vsibi.domain.network.response.ResponseHotel
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.IError
import ru.vsibi.presentation.screens.search.main.SearchViewState
import ru.vsibi.presentation.screens.tours.main.TourModel

sealed class ToursInfoState{
    class Loaded(val data : ResponseSearch.Result) : ToursInfoState()
    class Loading() : ToursInfoState()
    class Error(val error: IError?) : ToursInfoState()

}
