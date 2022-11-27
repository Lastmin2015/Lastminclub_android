package ru.vsibi.data.api.profile

import android.net.Uri
import okhttp3.MultipartBody
import retrofit2.Response
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.helper.Resource
import ru.vsibi.domain.network.post.PostLogin
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.domain.network.response.ResponseSignup

interface ProfileRepository {
    suspend fun get(): Resource<Response<ResponseProfile>>
    suspend fun update(postProfile: PostProfile): Resource<Response<Unit>>
    suspend fun changePassword(postChangePass: PostChangePass): Resource<Response<Unit>>
    suspend fun uploadAvatar(profileImage: MultipartBody.Part): Resource<Response<Unit>>
}