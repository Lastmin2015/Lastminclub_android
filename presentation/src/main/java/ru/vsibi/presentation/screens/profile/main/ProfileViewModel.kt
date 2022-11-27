package ru.vsibi.presentation.screens.profile.main

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.vsibi.data.SharedPreferenceService
import ru.vsibi.data.api.profile.ProfileRepository
import ru.vsibi.helper.Status
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseViewModel
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceService,
    private val profileRepository: ProfileRepository
) : BaseViewModel<ProfileViewState, ProfileAction, ProfileEvent>() {

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.LogOut -> signout()
            is ProfileEvent.FetchProfile -> fetchProfile()
            is ProfileEvent.UploadPhoto -> uploadPhoto(
                viewEvent.contentResolver,
                viewEvent.photoUri
            )
        }
    }

    private fun fetchProfile() {
        viewState = ProfileViewState.LoadingData()
        viewModelScope.launchOnIO {
            val response = profileRepository.get()
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        viewState = ProfileViewState.Loaded(response.data?.body()?.result)
                    }
                    Status.ERROR -> {
                        viewState = ProfileViewState.Error(response.error)
                    }
                }
            }
        }
    }

    private fun uploadPhoto(contentResolver: ContentResolver, photoUri: String?) {
        viewState = ProfileViewState.LoadingPhoto()
        viewModelScope.launchOnIO {
            if (photoUri == null) return@launchOnIO
            val file = File(getRealPathFromURI(contentResolver, photoUri?.toUri()))
            val requestFile = RequestBody.create(
                contentResolver.getType(photoUri.toUri())!!.toMediaTypeOrNull(),
                file
            )
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            val response = profileRepository.uploadAvatar(body)
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        viewState = ProfileViewState.LoadedPhoto(photoUri)
                    }
                    Status.ERROR -> {
                        viewState = ProfileViewState.Error(response.error)
                    }
                }
            }
        }
    }

    private fun getRealPathFromURI(contentResolver: ContentResolver, contentURI: Uri): String? {
        val result: String?
        val cursor: Cursor? = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }


    private fun signout() {
        sharedPref.clear()
        viewState = ProfileViewState.LoggedOut()
        viewAction = ProfileAction.ShowSnackBar(R.string.message_sign_out)
    }
}