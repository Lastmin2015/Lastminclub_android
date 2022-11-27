package ru.vsibi.data.api.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import ru.vsibi.data.LastminData
import ru.vsibi.domain.network.post.PostLogin
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.domain.network.response.ResponseSignup

interface AuthService {

    @Headers("Accept: application/json")
    @POST("/api/${LastminData.ApiVersion}/auth/sign-up")
    suspend fun signup(
        @Body postCommand: PostSignup
    ): Response<ResponseSignup>

    @Headers("Accept: application/json")
    @POST("/api/${LastminData.ApiVersion}/auth/sign-in")
    suspend fun login(
        @Body postCommand: PostLogin
    ): Response<ResponseSignup>

    @Headers("Accept: application/json")
    @POST("/api/${LastminData.ApiVersion}/auth/auth_social")
    suspend fun authSocial(
        @Query("token") token: String,
        @Query("social_type") socialType: String,
    ): Response<ResponseSignup>

}