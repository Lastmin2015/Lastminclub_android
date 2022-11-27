package ru.vsibi.data.api.profile

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import ru.vsibi.data.LastminData
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.domain.network.post.PostLogin
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.domain.network.response.ResponseSignup

interface ProfileService {

    @Headers("Accept: application/json")
    @POST("/api/${LastminData.ApiVersion}/profile")
    suspend fun update(
        @Body postProfile: PostProfile
    ): Response<Unit>

    @Headers("Accept: application/json")
    @GET("/api/${LastminData.ApiVersion}/profile")
    suspend fun get(): Response<ResponseProfile>

    @Headers("Accept: application/json")
    @POST("/api/${LastminData.ApiVersion}/profile/change-password")
    suspend fun changePass(
        @Body postChangePass: PostChangePass
    ): Response<Unit>

    @Headers("Accept: application/json")
    @Multipart
    @POST("/api/${LastminData.ApiVersion}/profile/upload-avatar")
    suspend fun uploadAvatar(
        @Part file: MultipartBody.Part
    ): Response<Unit>

}