package ru.vsibi.presentation.screens.login.emailVariant.createPassword

sealed class CreatePassEvent {
    class Default : CreatePassEvent()
    class SignIn(val pass : String) : CreatePassEvent()
    class ConfigureArgs (val data : String) : CreatePassEvent()
}
