package com.rodtech.repository.impl

import com.rodtech.config.dao.DatabaseFactory.dbQuery
import com.rodtech.model.Task
import com.rodtech.model.Tasks
import com.rodtech.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class TaskRepositoryImpl : TaskRepository {
    override suspend fun getAll(): List<Task> = dbQuery {
        Tasks.selectAll().map(::resultRowToTask)
    }

    override suspend fun getById(id: Long): Task? = dbQuery {
        Tasks
            .select{ Tasks.id eq id }
            .map(::resultRowToTask)
            .singleOrNull()
    }

    override suspend fun add(task: Task): Task? = dbQuery {
        val insertStatement = Tasks.insert {
            it[Tasks.title] = task.title
            it[Tasks.description] = task.description
            it[Tasks.done] = task.done
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTask)
    }

    override suspend fun edit(task: Task): Boolean = dbQuery {
        Tasks.update({ Tasks.id eq task.id!!}) {
            it[Tasks.title] = task.title
            it[Tasks.description] = task.description
            it[Tasks.done] = task.done
        } > 0
    }

    override suspend fun delete(id: Long): Boolean = dbQuery {
        Tasks.deleteWhere { Tasks.id eq id } > 0
    }

    private fun resultRowToTask(row: ResultRow) = Task(
        id = row[Tasks.id],
        title = row[Tasks.title],
        description = row[Tasks.description],
        done = row[Tasks.done]
    )
}

var taskRepository: TaskRepository = TaskRepositoryImpl().apply {
    runBlocking {
        if(getAll().isEmpty()){
            add(task= Task(title = "Hello task", description = "task hello start", done = false))
        }
    }
}
