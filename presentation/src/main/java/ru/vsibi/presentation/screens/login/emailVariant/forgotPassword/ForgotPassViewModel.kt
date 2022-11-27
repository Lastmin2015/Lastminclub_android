package ru.vsibi.presentation.screens.login.emailVariant.forgotPassword

import ru.vsibi.presentation.base.BaseViewModel

class ForgotPassViewModel : BaseViewModel<ForgotPassViewState, ForgotPassAction, ForgotPassEvent>() {

    override fun obtainEvent(viewEvent: ForgotPassEvent) {
        when(viewEvent){
            is ForgotPassEvent.SendResetLink -> sendResetLink(viewEvent.email)
        }
    }

    private fun sendResetLink(email: String) {
        viewState = ForgotPassViewState.OnSentLink(email)
    }

}