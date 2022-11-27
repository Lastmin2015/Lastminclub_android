package ru.vsibi.data.api.hotels

import retrofit2.Response
import ru.vsibi.domain.network.response.ResponseHotel
import ru.vsibi.domain.network.response.ResponseSignup
import ru.vsibi.helper.RemoteRepository
import ru.vsibi.helper.Resource
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(private val hotelsService: HotelsService) : HotelsRepository {

    override suspend fun fetchHotel(hotelId: String) =
        RemoteRepository<Response<ResponseHotel>>().request {
            hotelsService.fetchHotel(hotelId, true)
        }

}