package com.rodtech.routes

import com.rodtech.model.Task
import com.rodtech.service.impl.taskService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.taskRouting() {
    route("/task") {
        get {
            val list = taskService.getAll()
            if (list.isNotEmpty()) {
                call.respond(list)
            } else {
                call.respondText("No taks found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters.getOrFail<Long>("id").toLong()
            val task = taskService.getById(id) ?: return@get call.respondText(
                "No task with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(task)
        }
        post {
            val task = call.receive<Task>()
            val result = taskService.add(task) ?: return@post call.respond(HttpStatusCode.BadRequest)
            call.respond(HttpStatusCode.Created, result)
        }
        put {
            val task = call.receive<Task>()
            if(taskService.edit(task)) {
                call.respondText("Task edited", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Task not edited", status = HttpStatusCode.BadRequest)
            }
        }
        delete("{id?}") {
            val id = call.parameters.getOrFail<Long>("id").toLong()
            if(taskService.delete(id)) {
                call.respondText("Task removed", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}