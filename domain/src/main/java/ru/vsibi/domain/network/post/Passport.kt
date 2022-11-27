package ru.vsibi.domain.network.post

data class Passport(
    val date_of_expire: String,
    val issue_country: String,
    val number: String
)