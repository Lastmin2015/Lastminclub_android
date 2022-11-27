package ru.vsibi.presentation.screens.login.emailVariant.forgotPassword

sealed class ForgotPassEvent {
    class SendResetLink(val email: String) : ForgotPassEvent()
}
