package ru.vsibi.domain.network.post

data class PostChangePass(
    val new_password: String,
    val old_password: String
)