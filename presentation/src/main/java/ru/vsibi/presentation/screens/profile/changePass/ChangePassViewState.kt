package ru.vsibi.presentation.screens.profile.changePass

import ru.vsibi.helper.IError
import ru.vsibi.presentation.screens.profile.main.ProfileViewState

sealed class ChangePassViewState {
    class Loading() : ChangePassViewState()
    class PasswordChanged() : ChangePassViewState()
    class Error(val error: IError?) : ChangePassViewState()
}