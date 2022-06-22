package com.rodtech.service

import com.rodtech.model.Task

interface TaskService {
    suspend fun getAll(): List<Task>
    suspend fun getById(id: Long): Task?
    suspend fun add(task: Task): Task?
    suspend fun edit(task: Task): Boolean
    suspend fun delete(id: Long): Boolean
}