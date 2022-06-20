package com.rodtech.plugins

import com.rodtech.routes.taskRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        taskRouting()
    }
}
