package ru.vsibi.presentation.screens.login

import com.google.android.gms.auth.api.signin.GoogleSignInClient

sealed class LoginEvent {
    class LoginWithFacebook(val accessToken : String) : LoginEvent()
    class LoginWithGoogle(val accessToken : String) : LoginEvent()
}
