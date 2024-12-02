package pe.com.master.machines.data.repositoryImpl.remote

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.constants.http.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.local.database.RecipeEntityDataRepository
import pe.com.master.machines.data.repository.remote.RecipeDataRepository
import pe.com.master.machines.network.repository.RecipeNetworkRepository
import javax.inject.Inject

class RecipeDataRepositoryImpl @Inject constructor(
    private val recipeNetworkRepository: RecipeNetworkRepository,
    private val recipeEntityDataRepository: RecipeEntityDataRepository
) : RecipeDataRepository {

    override fun getLoadAllRecipes() = recipeNetworkRepository.getLoadAllRecipes()
        .map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.errorType)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .transform { resource ->
            if (resource is Resource.Success) {
                val updatedRecipes = resource.data.map { recipe ->
                    val result = recipeEntityDataRepository.recipeById(recipe.id).first()
                    if (result is Resource.Success) {
                        recipe.copy(isSaved = result.data)
                    } else {
                        recipe.copy(isSaved = false)
                    }
                }
                emit(Resource.Success(updatedRecipes))
            } else {
                emit(resource)
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

    override fun getRecipeById(id: Int) = recipeNetworkRepository.getRecipeById(id)
        .map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.errorType)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .transform { resource ->
            if (resource is Resource.Success) {
                val recipe = resource.data
                val result = recipeEntityDataRepository.recipeById(recipe.id).first()
                if (result is Resource.Success) {
                    recipe.copy(isSaved = result.data)
                } else {
                    recipe.copy(isSaved = false)
                }
                emit(Resource.Success(recipe))
            } else {
                emit(resource)
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }
}