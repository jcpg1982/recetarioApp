package pe.com.master.machines.constants.http

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorType: ErrorType) : Resource<T>()
}