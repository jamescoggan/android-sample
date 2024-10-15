package com.jamescoggan.data.model

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    class Loading<T> : Resource<T>
    data class Error<T>(val resourceError: ResourceError) : Resource<T>
}

sealed interface ResourceError {
    data object NoConnection : ResourceError
    data object NotFound : ResourceError
    data object Unauthorized : ResourceError
    data object Timeout : ResourceError
    data class Fatal(val throwable: Throwable) : ResourceError
}