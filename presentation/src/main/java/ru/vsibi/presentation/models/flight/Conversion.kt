package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Conversion(
    val EUR: Int
): Parcelable