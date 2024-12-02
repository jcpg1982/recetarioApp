package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.network.model.RecipeNetwork

interface RecipeNetworkRepository {

    fun getLoadAllRecipes(): Flow<Resource<List<RecipeNetwork>>>
    fun getRecipeById(id: Int): Flow<Resource<RecipeNetwork>>
}