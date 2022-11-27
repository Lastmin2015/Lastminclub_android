package ru.vsibi.presentation.screens.tours.info

import androidx.annotation.NonNull
import ru.vsibi.domain.network.response.ResponseSearch

sealed class ToursInfoEvent {
    class ConfigureArgs(val data: ResponseSearch.Result) : ToursInfoEvent()
    class FetchHotel(val hotelId: String) : ToursInfoEvent()
}
