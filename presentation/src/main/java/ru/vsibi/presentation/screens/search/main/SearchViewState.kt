package ru.vsibi.presentation.screens.search.main

import ru.vsibi.domain.network.response.ResponseCountryNames
import ru.vsibi.helper.IError

sealed class SearchViewState {
    class SearchLoading() : SearchViewState()
    class SearchFinished() : SearchViewState()
    class SearchInvalidFields(
        val isInvalidOrigin : Boolean,
        val isInvalidDestination : Boolean
    ) : SearchViewState()

    class LoadedData(val list : List<String>) : SearchViewState()
    class LoadingData() : SearchViewState()
    class ErrorData(val error: IError?) : SearchViewState()

    class Default() : SearchViewState()
    class Error(val error: IError?) : SearchViewState()
}