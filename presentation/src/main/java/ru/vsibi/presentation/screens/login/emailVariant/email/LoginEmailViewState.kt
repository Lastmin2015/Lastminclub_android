package ru.vsibi.presentation.screens.login.emailVariant.email

sealed class LoginEmailViewState {
    class OnLoginEntered(val data : String) : LoginEmailViewState()
    class Default() : LoginEmailViewState()
    class ValidError(val stringId : Int) : LoginEmailViewState()
}