package ru.vsibi.domain.network.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostLogin(
    var email: String = "",
    var password: String = "",
) : Parcelable
