package ru.vsibi.presentation.screens.profile.main

import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.helper.IError
import ru.vsibi.presentation.screens.login.LoginViewState

sealed class ProfileViewState {
    class LoggedOut() : ProfileViewState()
    class LoadingData() : ProfileViewState()
    class Loaded(val result: ResponseProfile.Result?) : ProfileViewState()
    class LoadingPhoto() : ProfileViewState()
    class LoadedPhoto(val photo : String?) : ProfileViewState()
    class Error(val error: IError?) : ProfileViewState()
}
