package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Recipe
import pe.com.master.machines.network.model.RecipeNetwork

fun RecipeNetwork.asModel() = Recipe(
    descripcion = this.descripcion.orEmpty(),
    id = this.id ?: -1,
    imagen = this.imagen.orEmpty(),
    ingredientes = this.ingredientes.orEmpty(),
    nombre = this.nombre.orEmpty(),
    pasos = this.pasos.orEmpty(),
)

fun List<RecipeNetwork>?.asModel() = this?.map { it.asModel() } ?: emptyList()