package pe.com.master.machines.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.com.master.machines.database.database.dao.DaoRecipe
import pe.com.master.machines.database.database.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun recipeDao(): DaoRecipe

}