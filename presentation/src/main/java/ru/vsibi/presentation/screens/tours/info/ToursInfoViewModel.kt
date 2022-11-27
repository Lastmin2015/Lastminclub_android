package ru.vsibi.presentation.screens.tours.info

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vsibi.data.api.hotels.HotelsRepository
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.Error
import ru.vsibi.helper.Status
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.screens.search.main.SearchViewState
import ru.vsibi.presentation.screens.tours.main.TourModel
import javax.inject.Inject

@HiltViewModel
class ToursInfoViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    BaseViewModel<ToursInfoState, ToursInfoAction, ToursInfoEvent>() {

    var tour: ResponseSearch.Result? = null

    override fun obtainEvent(viewEvent: ToursInfoEvent) {
        when (viewEvent) {
            is ToursInfoEvent.ConfigureArgs -> configureArgs(viewEvent.data)
//            is ToursInfoEvent.FetchHotel -> fetchHotel(viewEvent.hotelId)
        }
    }

    private fun configureArgs(data: ResponseSearch.Result) {
        this.tour = data
        this.tour?.tour?.hotel?.hotel_images?.sortBy {
            it.order
        }
        viewState = ToursInfoState.Loaded(data)
    }

//    private fun fetchHotel(hotelId: String) {
//        viewState = ToursInfoState.Loading()
//
//        viewModelScope.launchOnIO {
//            val response = hotelsRepository.fetchHotel(hotelId)
//            withContext(Dispatchers.Main) {
//                when (response.status) {
//                    Status.SUCCESS -> {
//                        response.data?.body()?.result.let {
//                            if (it == null) {
//                                viewState = ToursInfoState.Error(Error.default())
//                            } else {
//                                viewState = ToursInfoState.Loaded(it)
//                            }
//                        }
//
//                    }
//                    Status.ERROR -> {
//                        viewState = ToursInfoState.Error(response.error)
//                    }
//                }
//            }
//        }
//    }
}