package pe.com.master.machines.detail_recipe.screen

sealed interface DetailRecipeState {

    data object First : DetailRecipeState
    data object Loading : DetailRecipeState
    data object Unauthorized : DetailRecipeState
    data class Error(val throwable: Throwable) : DetailRecipeState
    data object Success : DetailRecipeState
}