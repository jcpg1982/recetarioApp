package pe.com.master.machines.constants.http

sealed class ErrorType {

    sealed class Api : ErrorType() {

        data class Network(val message: String) : Api()

        data class Unauthorized(val message: String) : Api()

        data class ServiceUnavailable(val message: String) : Api()

        data class NotFound(val message: String) : Api()

        data class Server(val message: String) : Api()

        data class Timeout(val message: String) : Api()

    }

    data class Unknown(val message: String) : ErrorType()
}