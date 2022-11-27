package ru.vsibi.presentation.screens.login.emailVariant.createPassword

import ru.vsibi.helper.IError

sealed class CreatePassViewState {
    class Loaded(val data: String) : CreatePassViewState()
    class LoggedIn : CreatePassViewState()
    class Loading : CreatePassViewState()
    class Default : CreatePassViewState()
    class Error(val error : IError?) : CreatePassViewState()
}
