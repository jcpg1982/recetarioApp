package pe.com.master.machines.database.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val descripcion: String,
    val imagen: String,
    val ingredientes: String,
    val nombre: String,
    val pasos: String
)