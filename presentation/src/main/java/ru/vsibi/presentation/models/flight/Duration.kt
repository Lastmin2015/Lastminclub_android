package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Duration(
    val departure: Int,
    val `return`: Int,
    val total: Long
): Parcelable