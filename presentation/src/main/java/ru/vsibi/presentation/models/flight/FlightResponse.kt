package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightResponse(
    val result: List<FlightResult>,
    val success: Boolean
) : Parcelable