package ru.vsibi.presentation.screens.search.main

import ru.vsibi.domain.network.query.QuerySearchModel

sealed class SearchEvent {
    class FetchCountries() : SearchEvent()
    class StartSearch(val searchModel : QuerySearchModel) : SearchEvent()
    class UpdatePersonsDesc(val data : String) : SearchEvent()
    class UpdateDate(val data : String) : SearchEvent()
    class UpdateCountry(val data : String) : SearchEvent()
    class Default() : SearchEvent()
}
