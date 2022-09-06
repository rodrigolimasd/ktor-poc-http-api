package com.rodtech.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Task(val id: Long? = null, val title:String, val description: String, val done: Boolean)

object Tasks : Table() {
    val id = long("id").autoIncrement()
    val title = varchar("title", 128)
    val description = varchar("description", 1024)
    val done = bool("done")

    override val primaryKey = PrimaryKey(id)
}
