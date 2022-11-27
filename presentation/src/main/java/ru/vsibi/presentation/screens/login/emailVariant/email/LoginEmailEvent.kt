package ru.vsibi.presentation.screens.login.emailVariant.email

sealed class LoginEmailEvent{
    class ContinueWithEmail(val email : String) : LoginEmailEvent()
    class Default : LoginEmailEvent()
}
