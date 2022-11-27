package ru.vsibi.data.api.search

import retrofit2.Response
import retrofit2.http.*
import ru.vsibi.data.LastminData
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.response.ResponseCountryNames
import ru.vsibi.domain.network.response.ResponseSearch

interface SearchService {


    @Headers("Accept: application/json")
    @GET("/api/${LastminData.ApiVersion}/tour-tickets/search")
    suspend fun searchTourTickets(
        @Query(value = "origin") origin : String,
        @Query(value = "destination") destination : String,
        @Query(value = "start_date") startDate : Long,
        @Query(value = "end_date") endDate : Long,
        @Query(value = "adults") adults : Int,
        @Query(value = "children") children : Int,
        @Query(value = "with_data") withData : Boolean
    ): Response<ResponseSearch>

    @Headers("Accept: application/json")
    @GET("/api/${LastminData.ApiVersion}/countries-names")
    suspend fun fetchCountryNames(): Response<ResponseCountryNames>

}