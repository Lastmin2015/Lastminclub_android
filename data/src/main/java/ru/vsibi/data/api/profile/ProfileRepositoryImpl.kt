package ru.vsibi.data.api.profile

import android.net.Uri
import android.os.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.helper.RemoteRepository
import ru.vsibi.helper.Resource
import java.io.File
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(private val profileService: ProfileService) : ProfileRepository {

    override suspend fun get() = RemoteRepository<Response<ResponseProfile>>().request {
        profileService.get()
    }

    override suspend fun update(postProfile: PostProfile) = RemoteRepository<Response<Unit>>().request {
        profileService.update(postProfile)
    }

    override suspend fun changePassword(postChangePass: PostChangePass) = RemoteRepository<Response<Unit>>().request {
        profileService.changePass(postChangePass)
    }

    override suspend fun uploadAvatar(profileImage: MultipartBody.Part) = RemoteRepository<Response<Unit>>().request {
        profileService.uploadAvatar(profileImage)
    }
}