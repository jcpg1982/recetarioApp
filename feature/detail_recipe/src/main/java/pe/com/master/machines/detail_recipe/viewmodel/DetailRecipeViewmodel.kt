package pe.com.master.machines.detail_recipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.constants.http.stringErrorMessage
import pe.com.master.machines.detail_recipe.screen.DetailRecipeState
import pe.com.master.machines.domain.local.database.SaveFavoriteUsesCase
import pe.com.master.machines.domain.local.database.UnSaveFavoriteUsesCase
import pe.com.master.machines.domain.remote.GetRecipesByIdUsesCase
import pe.com.master.machines.model.model.Recipe
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewmodel @Inject constructor(
    private val getRecipesByIdUsesCase: GetRecipesByIdUsesCase,
    private val saveFavoriteUsesCase: SaveFavoriteUsesCase,
    private val unSaveFavoriteUsesCase: UnSaveFavoriteUsesCase
) : ViewModel() {

    private val _initRecipe: MutableStateFlow<DetailRecipeState> =
        MutableStateFlow(DetailRecipeState.First)
    val initRecipe = _initRecipe.asStateFlow()
    private val _dataRecipe = MutableStateFlow(Recipe())
    val dataRecipe = _dataRecipe.asStateFlow()

    fun initRecipe(recipeId: Int, retryCount: Int = 0) {
        viewModelScope.launch {
            getRecipesByIdUsesCase.invoke(recipeId).flowOn(Dispatchers.IO).onStart {
                _initRecipe.update {
                    DetailRecipeState.Loading
                }
            }.catch { e ->
                if (retryCount < 3) initRecipe(recipeId, retryCount + 1)
                else _initRecipe.update { DetailRecipeState.Error(e) }
            }.collect { res ->
                when (res) {
                    is Resource.Error -> {
                        if (retryCount < 3) initRecipe(recipeId, retryCount + 1)
                        else _initRecipe.update {
                            DetailRecipeState.Error(Throwable(res.stringErrorMessage))
                        }
                    }

                    is Resource.Success -> {
                        _dataRecipe.update { res.data }
                        _initRecipe.update { DetailRecipeState.Success }
                    }
                }
            }
        }
    }

    fun saveFavorite(recipe: Recipe) {
        viewModelScope.launch {
            saveFavoriteUsesCase.invoke(recipe).flowOn(Dispatchers.IO).onStart { }.catch { e -> }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {}

                        is Resource.Success -> {
                            _dataRecipe.update { recipe ->
                                recipe.copy(isSaved = true)
                            }
                        }
                    }
                }
        }
    }

    fun unSaveFavorite(recipeId: Int) {
        viewModelScope.launch {
            unSaveFavoriteUsesCase.invoke(recipeId).flowOn(Dispatchers.IO).onStart { }
                .catch { e -> }.collect { res ->
                    when (res) {
                        is Resource.Error -> {}

                        is Resource.Success -> {
                            _dataRecipe.update { recipe ->
                                recipe.copy(isSaved = false)
                            }
                        }
                    }
                }
        }
    }
}