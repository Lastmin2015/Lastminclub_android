package ru.vsibi.domain.network.post

data class PostProfile(
    val birthday: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val middle_name: String,
    val passport: Passport,
    val phone: String,
    val picture_file_name: String
)