package ru.vsibi.helper

interface IError {
    fun getErrorMessage() : String?
    fun getErrorCode() : Int?
    fun getErrorResource() : Int
}