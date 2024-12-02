package pe.com.master.machines.database.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.com.master.machines.database.database.entity.RecipeEntity

@Dao
interface DaoRecipe {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(entities: RecipeEntity)

    @Query("DELETE FROM RecipeEntity WHERE id = :id")
    suspend fun unSaveFavorite(id: Int)

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun recipeById(id: Int): RecipeEntity?
}