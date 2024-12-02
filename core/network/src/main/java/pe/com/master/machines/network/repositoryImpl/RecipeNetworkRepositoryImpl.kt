package pe.com.master.machines.network.repositoryImpl

import kotlinx.coroutines.flow.flow
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.network.api.ApiService
import pe.com.master.machines.network.repository.RecipeNetworkRepository
import javax.inject.Inject

class RecipeNetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RecipeNetworkRepository {

    override fun getLoadAllRecipes() = flow {
        val response = apiService.getLoadAllRecipes()
        emit(Resource.Success(response))
    }

    override fun getRecipeById(id: Int) = flow {
        val response = apiService.getRecipeById(id)
        emit(Resource.Success(response))
    }
}