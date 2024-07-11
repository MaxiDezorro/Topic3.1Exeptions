package ru.netology

data class Comment(
    val id: Int, // идентефикатор коментария
    val fromId: Int, // идентификатор автора комментария
    val date: Int,
    val text: String
)