package com.rodtech.service.impl

import com.rodtech.model.Task
import com.rodtech.repository.impl.taskRepository
import com.rodtech.service.TaskService

class TaskServiceImpl : TaskService {
    override suspend fun getAll(): List<Task> = taskRepository.getAll()

    override suspend fun getById(id: Long) = taskRepository.getById(id)

    override suspend fun add(task: Task) = taskRepository.add(task)

    override suspend fun edit(task: Task) = taskRepository.edit(task)

    override suspend fun delete(id: Long) = taskRepository.delete(id)
}

var taskService: TaskService = TaskServiceImpl()