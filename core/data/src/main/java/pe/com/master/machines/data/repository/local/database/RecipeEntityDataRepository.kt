package pe.com.master.machines.data.repository.local.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.model.model.Recipe

interface RecipeEntityDataRepository {

    fun saveFavorite(recipes: Recipe): Flow<Resource<Unit>>
    fun unSaveFavorite(id: Int): Flow<Resource<Unit>>
    fun recipeById(recipeId: Int): Flow<Resource<Boolean>>
}