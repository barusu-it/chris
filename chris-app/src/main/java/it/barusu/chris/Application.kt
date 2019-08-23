package it.barusu.chris

import io.vertx.core.Vertx
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import javax.annotation.Resource

@SpringBootApplication
class Application {

    @Resource
    lateinit var httpServer: HttpServer

    @EventListener
    fun deployVerticle(event: ApplicationReadyEvent) {
        Vertx.vertx().deployVerticle(httpServer)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}