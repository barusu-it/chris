package it.barusu.chris

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.*
import it.barusu.chris.cashier.query.CashierPayment
import it.barusu.chris.cashier.query.CashierPaymentManager
import it.barusu.chris.util.StringUtils
import it.barusu.chris.util.json.JsonProviderHolder.Companion.JACKSON
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class HttpServer : AbstractVerticle() {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Resource
    lateinit var cashierPaymentManager: CashierPaymentManager

    override fun start() {
        val router = Router.router(vertx)
        router.route("/static").handler(StaticHandler.create())
        router.route().handler(LoggerHandler.create(LoggerFormat.SHORT))
        router.route().handler(BodyHandler.create())

        router.routeWithRegex(".*").failureHandler(ErrorHandler.create())

        router.post("/cashier/payment").handler {
            log.info("processing /cashier/payment.")
            var cashierPayment = JACKSON.parse(it!!.getBodyAsString(StringUtils.UTF_8),
                    CashierPayment::class.java)

            cashierPayment = cashierPaymentManager.save(cashierPayment)
            it.response().end(JACKSON.convert(cashierPayment))
        }

        router.get("/cashier/payment/:id").handler {
            val id = it.request().getParam("id").toLong()
            it.response().end(JACKSON.convert(cashierPaymentManager.fundById(id)))
        }

        // TODO it will be 500 if 404 by ErrorHandler, @see io.vertx.ext.web.impl.RoutingContextImpl#checkHandleNoMatch
        router.route().last().handler(ErrorHandler.create())

        vertx.createHttpServer().requestHandler(router).listen(8080)
    }


}