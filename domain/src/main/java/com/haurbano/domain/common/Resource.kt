package com.haurbano.domain.common

data class Resource<out T> (val status: Status, val data: T?, val error: ErrorEntity?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, error = null)

        fun <T> error(data: T? = null, error: ErrorEntity): Resource<T> =
            Resource(status = Status.ERROR, data = data, error = error)

        fun <T> loading(data: T? = null): Resource<T> = Resource(status = Status.LOADING, data = data, error = null)
    }
}