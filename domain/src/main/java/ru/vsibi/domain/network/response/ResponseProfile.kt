package ru.vsibi.domain.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ResponseProfile(
    val result: Result,
    val success: Boolean
) {
    data class Result(
        val birthday: String,
        val created_at: Int,
        val email: String,
        val first_name: String,
        val id: String,
        val last_name: String,
        val locale: String,
        val middle_name: String,
        val passport: Passport,
        val password: String,
        val phone: String,
        val picture_file_name: String,
        val role: Int,
        val token_reset: String,
        val updated_at: Int
    ) {
        @Parcelize
        data class Passport(
            val date_of_expiry: String,
            val issue_country: String,
            val number: String
        ) : Parcelable
    }
}