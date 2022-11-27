package ru.vsibi.presentation.screens.profile.main

import android.content.ContentResolver

sealed class ProfileEvent {
    class LogOut() : ProfileEvent()
    class FetchProfile() : ProfileEvent()
    class UploadPhoto(val contentResolver : ContentResolver, val photoUri: String?) : ProfileEvent()
}
