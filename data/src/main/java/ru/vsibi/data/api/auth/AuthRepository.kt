package ru.vsibi.data.api.auth

import retrofit2.Response
import ru.vsibi.helper.Resource
import ru.vsibi.domain.network.post.PostLogin
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.domain.network.response.ResponseSignup

interface AuthRepository {
    suspend fun login(postAuth: PostLogin): Resource<Response<ResponseSignup>>
    suspend fun signup(postAuth: PostSignup): Resource<Response<ResponseSignup>>
    suspend fun authSocial(token : String, socialType : String): Resource<Response<ResponseSignup>>
}