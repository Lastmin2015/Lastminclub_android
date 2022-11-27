package ru.vsibi.helper

class Error(
    val message : String,
    val code : Int,
    private val resource : Int
) : IError {

    override fun getErrorMessage() = message

    override fun getErrorCode() = code

    override fun getErrorResource() = resource

    companion object {
        fun default() = Error("Something went wrong", 0, R.string.something_went_wrong)
    }
}