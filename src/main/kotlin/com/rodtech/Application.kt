package com.rodtech

import com.rodtech.config.dao.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.rodtech.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
