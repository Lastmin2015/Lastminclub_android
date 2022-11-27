package ru.vsibi.presentation.screens.login

import ru.vsibi.helper.IError

sealed class LoginViewState {
    class SignCanceled() : LoginViewState()
    class SignError(val message : String) : LoginViewState()
    class LoggedIn() : LoginViewState()
    class Loading() : LoginViewState()
    class Error(val error: IError?) : LoginViewState()
}
