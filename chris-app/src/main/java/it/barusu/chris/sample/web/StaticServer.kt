package it.barusu.chris.sample.web

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import org.springframework.stereotype.Component

@Component
class StaticServer : AbstractVerticle() {

    override fun start() {
        val router = Router.router(vertx)
        router.route().handler(StaticHandler.create())
        vertx.createHttpServer().requestHandler(router).listen(8080)
    }


}