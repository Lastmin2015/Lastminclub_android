package ru.vsibi.data.api.search

import android.net.Uri
import android.os.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.query.QuerySearchModel
import ru.vsibi.domain.network.response.ResponseCountryNames
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.RemoteRepository
import ru.vsibi.helper.Resource
import java.io.File
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) : SearchRepository {
    override suspend fun searchTourTickets(searchModel : QuerySearchModel): Resource<Response<ResponseSearch>> = RemoteRepository<Response<ResponseSearch>>().request {
        searchService.searchTourTickets(
            origin = searchModel.origin,
            destination = searchModel.destination,
            startDate = searchModel.startDate,
            endDate = searchModel.endDate,
            adults = searchModel.adults,
            children = searchModel.children,
            withData = searchModel.withData
        )
    }

    override suspend fun fetchCountryNames(): Resource<Response<ResponseCountryNames>> = RemoteRepository<Response<ResponseCountryNames>>().request {
        searchService.fetchCountryNames()
    }

}