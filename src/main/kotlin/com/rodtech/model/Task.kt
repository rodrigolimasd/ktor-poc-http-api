package com.rodtech.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(val title:String, val description: String, val done: Boolean)

val taskStorage = mutableListOf<Task>()
