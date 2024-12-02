package pe.com.master.machines.model.model

data class Recipe(
    val descripcion: String = "",
    val id: Int = -1,
    val imagen: String = "",
    val ingredientes: String = "",
    val nombre: String = "",
    val pasos: String = "",
    val isSaved: Boolean = false,
)