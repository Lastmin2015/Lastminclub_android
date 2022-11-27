package ru.vsibi.presentation.screens.profile.changePass

import ru.vsibi.domain.network.post.PostChangePass

sealed class ChangePassEvent {
    class ChangePassword(val postChangePass: PostChangePass) : ChangePassEvent()
}