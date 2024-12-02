package pe.com.master.machines.data.repositoryImpl.local.database

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.constants.http.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.local.database.RecipeEntityDataRepository
import pe.com.master.machines.database.repository.RecipeDatabaseRepository
import pe.com.master.machines.model.model.Recipe
import javax.inject.Inject

class RecipeEntityDataRepositoryImpl @Inject constructor(private val recipeDatabaseRepository: RecipeDatabaseRepository) :
    RecipeEntityDataRepository {

    override fun saveFavorite(recipes: Recipe) =
        recipeDatabaseRepository.saveFavorite(recipes.asModel()).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.errorType)
                is Resource.Success -> Resource.Success(res.data)
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun unSaveFavorite(id: Int) =
        recipeDatabaseRepository.unSaveFavorite(id).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.errorType)
                is Resource.Success -> Resource.Success(res.data)
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun recipeById(recipeId: Int) =
        recipeDatabaseRepository.recipeById(recipeId).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.errorType)
                is Resource.Success -> Resource.Success(res.data)
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }
}