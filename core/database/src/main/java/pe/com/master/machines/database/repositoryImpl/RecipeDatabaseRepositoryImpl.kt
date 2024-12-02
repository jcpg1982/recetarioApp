package pe.com.master.machines.database.repositoryImpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.database.database.DataBase
import pe.com.master.machines.database.database.entity.RecipeEntity
import pe.com.master.machines.database.repository.RecipeDatabaseRepository
import javax.inject.Inject

class RecipeDatabaseRepositoryImpl @Inject constructor(database: DataBase) :
    RecipeDatabaseRepository {

    private val TAG = RecipeDatabaseRepositoryImpl::class.java.simpleName

    private val centroCostoDao = database.recipeDao()

    override fun saveFavorite(entities: RecipeEntity) = flow {
        val result = centroCostoDao.saveFavorite(entities)
        emit(Resource.Success(result))
    }

    override fun unSaveFavorite(id: Int) = flow {
        val result = centroCostoDao.unSaveFavorite(id)
        emit(Resource.Success(result))
    }

    override fun recipeById(recipeId: Int) = flow {
        val result = centroCostoDao.recipeById(recipeId)
        emit(Resource.Success(result != null))
    }
}