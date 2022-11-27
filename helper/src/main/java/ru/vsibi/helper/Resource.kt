package ru.vsibi.helper

import androidx.annotation.MainThread

data class Resource<out T>(val status: Status, val data: T?, val error : IError?) {
    companion object {
        fun <T> success(code: Int, data: T?): Resource<T> =
            Resource(error = null, status = Status.SUCCESS, data = data)

        fun <T> error(error : IError): Resource<T> =
            Resource(error = error, status = Status.ERROR, data = null)

    }

    fun isSuccessfull(): Boolean {
        return status == Status.SUCCESS
    }

    @MainThread
    inline fun handle(onSuccess: (data: T) -> Unit, onError: (code: Int?) -> Unit) {
        if (isSuccessfull()) {
            onSuccess(data!!)
        } else {
            onError(error?.getErrorCode())
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR
}