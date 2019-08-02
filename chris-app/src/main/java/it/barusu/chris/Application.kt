package it.barusu.chris

import io.vertx.core.Vertx
import it.barusu.chris.sample.web.StaticServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import javax.annotation.Resource

@SpringBootApplication
class Application {

    @Resource
    lateinit var staticServer: StaticServer

    @EventListener
    fun deployVerticle(event: ApplicationReadyEvent) {
        Vertx.vertx().deployVerticle(staticServer)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}