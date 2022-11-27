package ru.vsibi.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalDataModel(
    val id: Int,
    val name: String,
    val dateOfBirth: String,
    val email: String,
    val phoneNumber: String,
    val passport: Passport,
    val picture : String
) : Parcelable {

    constructor(id: Int) : this(id, "", "", "", "", Passport("", "", ""), "")

    @Parcelize
    data class Passport(
        val number: String,
        val country: String,
        val dateOfExpiry: String
    ) : Parcelable
}