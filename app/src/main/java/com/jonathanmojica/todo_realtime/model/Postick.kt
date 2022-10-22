package com.jonathanmojica.todo_realtime.model

data class Categoria(
    val nombre:String? = "",
    val color: Int? = -1,
)

data class Subtarea(
    val nombre:String? = "",
    val activo:Boolean? = false
)

data class Postick(
    val id: String? = "",
    val color: Int? = -1,
    val titulo:String? = "",
    val categorias: List<Categoria>? = arrayListOf(),
    val subtareas: List<Subtarea>? = arrayListOf()
)

