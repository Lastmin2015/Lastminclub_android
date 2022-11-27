package ru.vsibi.presentation.screens.login.emailVariant.email

import android.text.TextUtils
import android.util.Patterns
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseViewModel


class LoginEmailViewModel : BaseViewModel<LoginEmailViewState, LoginEmailAction, LoginEmailEvent>() {

    override fun obtainEvent(viewEvent: LoginEmailEvent) {
        when (viewEvent) {
            is LoginEmailEvent.ContinueWithEmail -> continueWithEmail(viewEvent.email)
            is LoginEmailEvent.Default -> viewState = LoginEmailViewState.Default()
        }
    }

    private fun continueWithEmail(email: String) {
        if (!isValidEmail(email)) return
        viewState = LoginEmailViewState.OnLoginEntered(email)
    }

    private fun isValidEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            viewState = LoginEmailViewState.ValidError(R.string.empty_email)
            false
        } else {
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                true
            }else{
                viewState = LoginEmailViewState.ValidError(R.string.invalid_email)
                false
            }
        }
    }
}