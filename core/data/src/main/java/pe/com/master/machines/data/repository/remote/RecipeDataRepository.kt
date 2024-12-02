package pe.com.master.machines.data.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.model.model.Recipe

interface RecipeDataRepository {

    fun getLoadAllRecipes(): Flow<Resource<List<Recipe>>>
    fun getRecipeById(id: Int): Flow<Resource<Recipe>>
}