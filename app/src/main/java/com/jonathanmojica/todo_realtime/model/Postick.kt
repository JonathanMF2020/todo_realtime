package com.jonathanmojica.todo_realtime.model

import android.graphics.Color

data class Categoria(
    val nombre:String,
)

data class Subtarea(
    val nombre:String,
    val activo:Boolean
)

data class Postick(
    val color: Int,
    val titulo:String,
    val categorias: List<Categoria>,
    val subtareas: List<Subtarea>
)

