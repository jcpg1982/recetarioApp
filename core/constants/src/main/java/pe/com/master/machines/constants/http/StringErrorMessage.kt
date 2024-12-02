package pe.com.master.machines.constants.http

val <T> Resource<T>.stringErrorMessage
    get() = run {
        when (this) {
            is Resource.Error -> when (val error = this.errorType) {
                is ErrorType.Api.Network -> error.message
                is ErrorType.Api.NotFound -> error.message
                is ErrorType.Api.Server -> error.message
                is ErrorType.Api.ServiceUnavailable -> error.message
                is ErrorType.Api.Timeout -> error.message
                is ErrorType.Api.Unauthorized -> error.message
                is ErrorType.Unknown -> error.message
            }

            is Resource.Success -> "Unknown Error"

        }
    }