package ru.vsibi.presentation.screens.search.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vsibi.data.api.search.SearchRepository
import ru.vsibi.domain.network.query.QuerySearchModel
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.Error
import ru.vsibi.helper.Status
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel<SearchViewState, SearchAction, SearchEvent>() {

    private var searchResponse: ResponseSearch? = null

    override fun obtainEvent(viewEvent: SearchEvent) {
        when (viewEvent) {
            is SearchEvent.StartSearch -> {
                startSearch(viewEvent.searchModel)
            }
            is SearchEvent.Default -> viewState = SearchViewState.Default()
//            is SearchEvent.UpdateDate -> searchResponse.date = viewEvent.data
//            is SearchEvent.UpdateCountry -> searchResponse.country = viewEvent.data
//            is SearchEvent.UpdatePersonsDesc -> searchResponse.personsDesc = viewEvent.data
            is SearchEvent.FetchCountries -> {
                fetchCountries()
            }
        }
    }

    private fun fetchCountries() {
        viewState = SearchViewState.LoadingData()

        viewModelScope.launchOnIO {
            val response = searchRepository.fetchCountryNames()
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        val list = response.data?.body()?.result?.map { it.name }
                        viewState = if(list.isNullOrEmpty()){
                            SearchViewState.ErrorData(Error("Empty Data", -1, R.string.server_error))
                        }else{
                            SearchViewState.LoadedData(list)
                        }
                    }
                    Status.ERROR -> {
                        viewState = SearchViewState.ErrorData(response.error)
                    }
                }
            }
        }
    }

    private fun startSearch(searchModel: QuerySearchModel) {
        viewState = SearchViewState.SearchLoading()
        val isInvalidOrigin = searchModel.origin.isEmpty()
        val isInvalidDest = searchModel.destination.isEmpty()
        if(isInvalidOrigin || isInvalidDest){
            viewState = SearchViewState.SearchInvalidFields(isInvalidOrigin, isInvalidDest)
            return
        }

        viewModelScope.launchOnIO {
            val response = searchRepository.searchTourTickets(searchModel)
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        searchResponse = response.data?.body()
                        viewState = SearchViewState.SearchFinished()
                    }
                    Status.ERROR -> {
                        viewState = SearchViewState.Error(response.error)
                    }
                }
            }
        }
    }

    fun getSearchModel() = searchResponse
}