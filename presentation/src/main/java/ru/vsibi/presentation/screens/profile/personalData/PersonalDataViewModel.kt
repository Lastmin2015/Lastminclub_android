package ru.vsibi.presentation.screens.profile.personalData

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vsibi.data.api.profile.ProfileRepository
import ru.vsibi.domain.network.post.PostProfile
import ru.vsibi.helper.Status
import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.screens.profile.main.ProfileViewState
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor(private val profileRepository: ProfileRepository): BaseViewModel<PersonalDataViewState, PersonalDataAction, PersonalDataEvent>() {

    override fun obtainEvent(viewEvent: PersonalDataEvent) {
        when (viewEvent) {
            is PersonalDataEvent.Update -> updateProfile(viewEvent.postProfile)
        }
    }

    private fun updateProfile(postProfile: PostProfile) {
        viewState = PersonalDataViewState.Loading()
        viewModelScope.launchOnIO {
            val response = profileRepository.update(postProfile)
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        viewState = PersonalDataViewState.ProfileUpdated()
                    }
                    Status.ERROR -> {
                        viewState = PersonalDataViewState.Error(response.error)
                    }
                }
            }
        }
    }
}