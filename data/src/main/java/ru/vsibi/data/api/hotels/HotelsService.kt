package ru.vsibi.data.api.hotels

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.vsibi.data.LastminData
import ru.vsibi.domain.network.response.ResponseHotel
import ru.vsibi.domain.network.response.ResponseSearch

interface HotelsService {

    @Headers("Accept: application/json")
    @GET("/api/${LastminData.ApiVersion}/hotels/{id}")
    suspend fun fetchHotel(
        @Path(value = "id") id : String,
        @Query(value = "with_data") withData : Boolean
    ): Response<ResponseHotel>

}