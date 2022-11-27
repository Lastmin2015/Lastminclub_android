package ru.vsibi.presentation.screens.profile.changePass

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vsibi.data.api.profile.ProfileRepository
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.helper.Status
import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataViewState
import javax.inject.Inject

@HiltViewModel
class ChangePassViewModel @Inject constructor(private val profileRepository : ProfileRepository): BaseViewModel<ChangePassViewState, ChangePassAction, ChangePassEvent>() {


    override fun obtainEvent(viewEvent: ChangePassEvent) {
        when (viewEvent) {
            is ChangePassEvent.ChangePassword -> changePass(viewEvent.postChangePass)
        }
    }

    private fun changePass(postChangePass: PostChangePass) {
        viewState = ChangePassViewState.Loading()
        viewModelScope.launchOnIO {
            val response = profileRepository.changePassword(postChangePass)
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        viewState = ChangePassViewState.PasswordChanged()
                    }
                    Status.ERROR -> {
                        viewState = ChangePassViewState.Error(response.error)
                    }
                }
            }
        }
    }
}