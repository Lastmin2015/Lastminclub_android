package ru.vsibi.presentation.models.flight

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.vsibi.presentation.models.flight.Baglimit
import ru.vsibi.presentation.models.flight.BagsPrice
import ru.vsibi.presentation.models.flight.Conversion
import ru.vsibi.presentation.models.flight.Duration

@Parcelize
data class FlightResult (
    val baglimit: Baglimit,
    val bags_price: BagsPrice,
    val booking_token: String,
    val city_code_from: String,
    val city_code_to: String,
    val city_from: String,
    val city_to: String,
    val conversion: Conversion,
    val created_at: Int,
    val date_from: Long,
    val date_to: Long,
    val distance: Double,
    val duration: Duration,
    val fly_from: String,
    val fly_to: String,
    val id: String,
    val local_arrival: String,
    val local_departure: String,
    val price: Int,
    val quality: Double,
    val route: List<String>,
    val seats: Int,
    val updated_at: Int,
    val utc_arrival: String,
    val utc_departure: String,
    val stops : List<FlightResult>
): Parcelable