package ru.vsibi.presentation.screens.profile.personalData

import ru.vsibi.domain.network.post.PostProfile

sealed class PersonalDataEvent {
    class Update(val postProfile: PostProfile) : PersonalDataEvent()
}