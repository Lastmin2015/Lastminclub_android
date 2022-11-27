package ru.vsibi.presentation.screens.profile.personalData

import ru.vsibi.helper.IError
import ru.vsibi.presentation.screens.profile.main.ProfileViewState

sealed class PersonalDataViewState {
    class Loading : PersonalDataViewState()
    class ProfileUpdated : PersonalDataViewState()
    class Error(val error: IError?) : PersonalDataViewState()
}