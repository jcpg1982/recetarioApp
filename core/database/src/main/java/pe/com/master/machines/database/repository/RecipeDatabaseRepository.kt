package pe.com.master.machines.database.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.database.database.entity.RecipeEntity

interface RecipeDatabaseRepository {

    fun saveFavorite(entities: RecipeEntity): Flow<Resource<Unit>>
    fun unSaveFavorite(id: Int): Flow<Resource<Unit>>
    fun recipeById(recipeId: Int): Flow<Resource<Boolean>>
}