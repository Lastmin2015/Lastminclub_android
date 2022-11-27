package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BagsPrice(
    val `1`: Double,
    val `2`: Double
): Parcelable