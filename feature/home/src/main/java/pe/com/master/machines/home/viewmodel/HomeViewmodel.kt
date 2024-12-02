package pe.com.master.machines.home.viewmodel

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
import pe.com.master.machines.domain.local.database.SaveFavoriteUsesCase
import pe.com.master.machines.domain.local.database.UnSaveFavoriteUsesCase
import pe.com.master.machines.domain.local.preferences.InitFirstUsesCase
import pe.com.master.machines.domain.remote.GetRecipesAllUsesCase
import pe.com.master.machines.home.screen.HomeState
import pe.com.master.machines.model.model.Recipe
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val getRecipesAllUsesCase: GetRecipesAllUsesCase,
    private val saveFavoriteUsesCase: SaveFavoriteUsesCase,
    private val unSaveFavoriteUsesCase: UnSaveFavoriteUsesCase,
    private val initFirstUsesCase: InitFirstUsesCase,
) : ViewModel() {

    private val _loadAllRecipes: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.First)
    val loadAllRecipes = _loadAllRecipes.asStateFlow()
    private val _dataList = MutableStateFlow(emptyList<Recipe>())
    val dataList = _dataList.asStateFlow()
    private val _initFirst = MutableStateFlow(initFirstUsesCase.invoke())
    val initFirst = _initFirst.asStateFlow()

    init {
        loadAllRecipes()
    }

    fun initFirst(isFirst: Boolean) {
        initFirstUsesCase.invoke(isFirst)
        _initFirst.update { isFirst }
    }

    fun saveFavorite(recipe: Recipe) {
        viewModelScope.launch {
            saveFavoriteUsesCase.invoke(recipe).flowOn(Dispatchers.IO).onStart { }.catch { e -> }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {}

                        is Resource.Success -> {
                            _dataList.update { list ->
                                val updatedList = list.map {
                                    if (it.id == recipe.id) it.copy(isSaved = true) else it
                                }
                                sortRecipesByFavoriteStatus(updatedList)
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
                            _dataList.update { list ->
                                val updatedList = list.map {
                                    if (it.id == recipeId) it.copy(isSaved = false) else it
                                }
                                sortRecipesByFavoriteStatus(updatedList)
                            }
                        }
                    }
                }
        }
    }

    fun loadAllRecipes(retryCount: Int = 0) {
        viewModelScope.launch {
            getRecipesAllUsesCase.invoke().flowOn(Dispatchers.IO).onStart {
                _loadAllRecipes.update {
                    HomeState.Loading
                }
            }.catch { e ->
                if (retryCount < 3) loadAllRecipes(retryCount + 1)
                else _loadAllRecipes.update { HomeState.Error(e) }
            }.collect { res ->
                when (res) {
                    is Resource.Error -> {
                        if (retryCount < 3) loadAllRecipes(retryCount + 1)
                        else _loadAllRecipes.update {
                            HomeState.Error(Throwable(res.stringErrorMessage))
                        }
                    }

                    is Resource.Success -> {
                        _dataList.update { sortRecipesByFavoriteStatus(res.data) }
                        _loadAllRecipes.update { HomeState.Success }
                    }
                }
            }
        }
    }

    private fun sortRecipesByFavoriteStatus(recipes: List<Recipe>): List<Recipe> {
        val favorites = recipes.filter { it.isSaved }
        val nonFavorites = recipes.filterNot { it.isSaved }.sortedBy { it.id }
        return favorites + nonFavorites
    }
}