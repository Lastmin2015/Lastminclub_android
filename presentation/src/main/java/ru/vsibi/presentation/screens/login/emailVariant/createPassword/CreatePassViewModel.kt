package ru.vsibi.presentation.screens.login.emailVariant.createPassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vsibi.data.api.auth.AuthRepository
import ru.vsibi.domain.network.post.PostSignup
import ru.vsibi.helper.Status
import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.data.SharedPreferenceService
import javax.inject.Inject

@HiltViewModel
class CreatePassViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceService,
    private val authRepository: AuthRepository
) : BaseViewModel<CreatePassViewState, CreatePassAction, CreatePassEvent>() {

    private var email: String? = null

    override fun obtainEvent(viewEvent: CreatePassEvent) {
        when (viewEvent) {
            is CreatePassEvent.ConfigureArgs -> configureArgs(viewEvent.data)
            is CreatePassEvent.SignIn -> signIn(viewEvent.pass)
            is CreatePassEvent.Default -> viewState = CreatePassViewState.Default()
        }
    }

    private fun configureArgs(data: String) {
        this.email = data
        viewState = CreatePassViewState.Loaded(data)
    }

    private fun signIn(pass: String) {
        if (email == null) return
        sharedPref.setSpString(SharedPreferenceService.KEY_EMAIL, email)
        viewState = CreatePassViewState.Loading()
        viewModelScope.launchOnIO {
            val postAuth = PostSignup(email!!, pass)
            val response = authRepository.signup(postAuth)
            withContext(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        sharedPref.setSpString(SharedPreferenceService.KEY_AUTH, response.data?.body()?.result?.access_token)
                        viewState = CreatePassViewState.LoggedIn()
                    }
                    Status.ERROR -> {
                        viewState = CreatePassViewState.Error(response.error)
                    }
                }
            }
        }
    }
}