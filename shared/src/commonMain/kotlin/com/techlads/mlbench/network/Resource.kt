package com.techlads.mlbench.network

data class Resource<out T>(val status: Status, val  data: T? = null, val message: String? = null, val error: Throwable? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(failure: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, failure.message ?: "Something went wrong", failure)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}