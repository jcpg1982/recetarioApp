package pe.com.master.machines.domain.local.database

import pe.com.master.machines.data.repository.local.database.RecipeEntityDataRepository
import javax.inject.Inject

class UnSaveFavoriteUsesCase @Inject constructor(
    private var recipeEntityDataRepository: RecipeEntityDataRepository,
) {

    operator fun invoke(recipeId: Int) = recipeEntityDataRepository.unSaveFavorite(recipeId)
}