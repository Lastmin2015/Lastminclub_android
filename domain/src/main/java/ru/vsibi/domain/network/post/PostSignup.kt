package ru.vsibi.domain.network.post

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostSignup(
    var email: String = "",
    var password: String = "",
) : Parcelable
