package ru.vsibi.domain.network.response

data class ResponseSignup(
    val success : Boolean,
    val result : Result
    ) {}
class Result(val access_token : String)