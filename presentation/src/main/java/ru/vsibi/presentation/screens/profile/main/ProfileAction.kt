package ru.vsibi.presentation.screens.profile.main

import androidx.annotation.StringRes

sealed class ProfileAction {
    class ShowSnackBar(@StringRes val data : Int) : ProfileAction()
}
