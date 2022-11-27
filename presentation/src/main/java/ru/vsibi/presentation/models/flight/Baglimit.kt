package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Baglimit(
    val hand_height: Int,
    val hand_length: Int,
    val hand_weight: Int,
    val hand_width: Int,
    val hold_dimensions_sum: Int,
    val hold_height: Int,
    val hold_length: Int,
    val hold_weight: Int,
    val hold_width: Int
): Parcelable