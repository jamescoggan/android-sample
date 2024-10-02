package com.jamescoggan.plugins

import com.jamescoggan.data.FoodRepository
import com.jamescoggan.data.FoodRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val foodRepository: FoodRepository by lazy { FoodRepositoryImpl() }

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/favorites") {
            call.respond(foodRepository.getFavourite())
        }
        get("/recommended") {
            call.respond(foodRepository.getRecommended())
        }


        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
