package ru.vsibi.data.api.search

import retrofit2.Response
import retrofit2.http.*
import ru.vsibi.data.LastminData
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.query.QuerySearchModel
import ru.vsibi.domain.network.response.ResponseCountryNames
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.Resource

interface SearchRepository {

    suspend fun searchTourTickets(searchModel : QuerySearchModel): Resource<Response<ResponseSearch>>
    suspend fun fetchCountryNames(): Resource<Response<ResponseCountryNames>>

}