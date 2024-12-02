package pe.com.master.machines.constants.http

import com.chuckerteam.chucker.BuildConfig
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.toErrorType() = when (this) {
    is SocketTimeoutException -> ErrorType.Api.Timeout(message.orEmpty())
    is IOException -> ErrorType.Api.Network(message.orEmpty())
    is HttpException -> when (code()) {
        ErrorCodes.Http.RESOURCE_NOT_FOUND -> ErrorType.Api.NotFound(message.orEmpty())
        ErrorCodes.Http.INTERNAL_SERVER -> ErrorType.Api.Server(message.orEmpty())
        ErrorCodes.Http.SERVICE_UNAVAILABLE -> ErrorType.Api.ServiceUnavailable(message.orEmpty())
        ErrorCodes.Http.UNAUTHORIZED -> ErrorType.Api.Unauthorized(message.orEmpty())
        else -> ErrorType.Unknown(if (BuildConfig.DEBUG) this.message() else "")
    }

    else -> ErrorType.Unknown(message.orEmpty())
}