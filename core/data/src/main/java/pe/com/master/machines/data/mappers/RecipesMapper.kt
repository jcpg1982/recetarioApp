package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.database.entity.RecipeEntity
import pe.com.master.machines.model.model.Recipe

fun Recipe.asModel() = RecipeEntity(
    descripcion = this.descripcion,
    id = this.id,
    imagen = this.imagen,
    ingredientes = this.ingredientes,
    nombre = this.nombre,
    pasos = this.pasos,
)

fun List<Recipe>?.asModel() = this?.map { it.asModel() } ?: emptyList()