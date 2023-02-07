package com.pum.deskwatch

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isDone: Boolean)
