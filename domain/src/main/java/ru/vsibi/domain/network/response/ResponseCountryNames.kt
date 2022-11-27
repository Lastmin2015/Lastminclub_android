package ru.vsibi.domain.network.response

data class ResponseCountryNames(
    val result: List<Result>,
    val success: Boolean
) {
    data class Result(
        val country_id: String,
        val created_at: Int,
        val id: String,
        val locale: String,
        val name: String,
        val updated_at: Int
    )
}