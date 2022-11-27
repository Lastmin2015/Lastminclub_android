package ru.vsibi.presentation.screens.login.emailVariant.password

import ru.vsibi.helper.IError

sealed class LoginPasswordViewState {
    class Loaded(val data : String) : LoginPasswordViewState()
    class LoggedIn : LoginPasswordViewState()
    class Loading : LoginPasswordViewState()
    class Default : LoginPasswordViewState()
    class Error(val error: IError?) : LoginPasswordViewState()
}
