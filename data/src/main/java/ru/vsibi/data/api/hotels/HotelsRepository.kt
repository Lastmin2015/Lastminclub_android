package ru.vsibi.data.api.hotels

import retrofit2.Response
import ru.vsibi.domain.network.response.ResponseHotel
import ru.vsibi.helper.Resource

interface HotelsRepository {

    suspend fun fetchHotel(hotelId : String) : Resource<Response<ResponseHotel>>

}