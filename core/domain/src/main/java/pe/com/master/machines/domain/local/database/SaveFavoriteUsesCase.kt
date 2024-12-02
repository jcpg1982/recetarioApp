package pe.com.master.machines.domain.local.database

import pe.com.master.machines.data.repository.local.database.RecipeEntityDataRepository
import pe.com.master.machines.model.model.Recipe
import javax.inject.Inject

class SaveFavoriteUsesCase @Inject constructor(
    private var recipeEntityDataRepository: RecipeEntityDataRepository,
) {

    operator fun invoke(recipe: Recipe) = recipeEntityDataRepository.saveFavorite(recipe)
}