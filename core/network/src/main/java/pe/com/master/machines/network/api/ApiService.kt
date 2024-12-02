package pe.com.master.machines.network.api

import pe.com.master.machines.network.model.RecipeNetwork
import pe.com.master.machines.network.utils.Utils.Endpoints.LOAD_ALL
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(LOAD_ALL)
    suspend fun getLoadAllRecipes(): List<RecipeNetwork>

    @GET("$LOAD_ALL{id}")
    suspend fun getRecipeById(@Path("id") id: Int): RecipeNetwork
}