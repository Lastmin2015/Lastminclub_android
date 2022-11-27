package ru.vsibi.domain.network.query

data class QuerySearchModel(
    val origin : String,
    val destination : String,
    val startDate : Long,
    val endDate : Long,
    val adults : Int,
    val children : Int,
    val withData : Boolean
) {

}