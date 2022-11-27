package ru.vsibi.data.api.auth

import retrofit2.Response
import ru.vsibi.helper.RemoteRepository
import ru.vsibi.domain.network.post.PostLogin
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.domain.network.response.ResponseSignup
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) : AuthRepository {

    override suspend fun login(postAuth: PostLogin) =
        RemoteRepository<Response<ResponseSignup>>().request {
            authService.login(postAuth)
        }
    override suspend fun signup(postAuth: PostSignup) =
        RemoteRepository<Response<ResponseSignup>>().request {
            authService.signup(postAuth)
        }
    override suspend fun authSocial(token : String, socialType : String) =
        RemoteRepository<Response<ResponseSignup>>().request {
            authService.authSocial(token, socialType)
        }

}