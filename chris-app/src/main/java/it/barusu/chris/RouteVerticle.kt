package it.barusu.chris

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.*
import it.barusu.chris.cashier.CashierHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class RouteVerticle : AbstractVerticle() {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)

        @JvmStatic
        private val CONTEXT_STATIC = "/static"

        @JvmStatic
        private val CONTEXT_API = "/v"

        @JvmStatic
        private val CONTEXT_REGEX_ROOT_PATTERN = "\\/v\\/.*"

    }

    @Resource
    lateinit var cashierHandler: CashierHandler

    override fun start() {
        val router = Router.router(vertx)

        router.route("$CONTEXT_API$CONTEXT_STATIC").handler(StaticHandler.create())
        router.routeWithRegex(CONTEXT_REGEX_ROOT_PATTERN).handler(LoggerHandler.create(LoggerFormat.SHORT))
        router.routeWithRegex(CONTEXT_REGEX_ROOT_PATTERN).handler(BodyHandler.create())

        router.routeWithRegex(CONTEXT_REGEX_ROOT_PATTERN).failureHandler(ErrorHandler.create())

        router.mountSubRouter("$CONTEXT_API/cashier", cashierHandler.route(vertx))

        // if route path is not matched at last, it will be 404 with 'Resource not found' html response body,
        // @see io.vertx.ext.web.impl.RoutingContextImpl#checkHandleNoMatch
//        router.route().last().handler(ErrorHandler.create())

        vertx.createHttpServer().requestHandler(router).listen(8081)
    }


}