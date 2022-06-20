package com.rodtech.routes

import com.rodtech.model.Task
import com.rodtech.model.taskStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.taskRouting() {
    route("/task") {
        get {
            if (taskStorage.isNotEmpty()) {
                call.respond(taskStorage)
            } else {
                call.respondText("No taks found", status = HttpStatusCode.OK)
            }
        }
        get("{title?}") {
            val title = call.parameters["title"] ?: return@get call.respondText(
                "Missing title", status = HttpStatusCode.BadRequest
            )
            val task = taskStorage.find { it.title.equals(title, ignoreCase = true) } ?: return@get call.respondText(
                "No task with title $title",
                status = HttpStatusCode.NotFound
            )
            call.respond(task)
        }
        post {
            val task = call.receive<Task>()
            taskStorage.add(task)
            call.respondText("Task add", status = HttpStatusCode.Created)
        }
        delete("{title?}") {
            val title = call.parameters["title"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(taskStorage.removeIf{ it.title.equals(title, ignoreCase = true)}) {
                call.respondText("Task removed", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}