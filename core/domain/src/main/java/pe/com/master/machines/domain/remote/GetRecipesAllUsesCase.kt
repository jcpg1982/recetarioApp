package pe.com.master.machines.domain.remote

import pe.com.master.machines.data.repository.remote.RecipeDataRepository
import javax.inject.Inject

class GetRecipesAllUsesCase @Inject constructor(
    private var recipeDataRepository: RecipeDataRepository,
) {

    operator fun invoke() = recipeDataRepository.getLoadAllRecipes()
}