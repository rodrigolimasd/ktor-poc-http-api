package com.rodtech.repository

import com.rodtech.model.Task

interface TaskRepository {
    suspend fun getAll(): List<Task>
    suspend fun getById(id: Long): Task?
    suspend fun add(task: Task): Task?
    suspend fun edit(task: Task): Boolean
    suspend fun delete(id: Long): Boolean
}