package com.jonathanmojica.todo_realtime.model


data class PostickData(
    val uid: String? = "",
    val postick: Postick? = Postick()
)

data class Subtarea(
    val nombre:String? = "",
    val activo:Boolean? = false
)

data class Postick(
    val id: String? = "",
    val color: Int? = -1,
    var titulo:String? = "",
    var subtareas: ArrayList<Subtarea>? = arrayListOf()
)

