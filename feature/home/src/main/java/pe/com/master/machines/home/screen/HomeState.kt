package pe.com.master.machines.home.screen

sealed interface HomeState {

    data object First : HomeState
    data object Loading : HomeState
    data object Unauthorized : HomeState
    data class Error(val throwable: Throwable) : HomeState
    data object Success : HomeState
}