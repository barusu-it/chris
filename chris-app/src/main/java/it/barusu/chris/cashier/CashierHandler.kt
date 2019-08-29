package it.barusu.chris.cashier

import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.serviceproxy.ServiceProxyBuilder
import it.barusu.chris.cashier.query.CashierPayment
import it.barusu.chris.util.StringUtils
import it.barusu.chris.util.json.JsonProviderHolder.Companion.JACKSON
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.stereotype.Component
import java.util.*

/**
 * same as spring mvc controller
 */
@Component
class CashierHandler : BeanFactoryAware {


    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    lateinit var cashierPaymentAsyncService: CashierPaymentAsyncService

    lateinit var self: CashierHandler

    var vertx: Vertx? = null

    fun route(vertx: Vertx): Router {
        if (Objects.isNull(this.vertx)) {
            this.vertx = vertx
        }

        this.cashierPaymentAsyncService = ServiceProxyBuilder(vertx)
//                .setAddress(CashierPaymentAsyncService.ADDRESS)
                .setAddress(CashierPaymentAsyncService::class.java.name)
                .build(CashierPaymentAsyncService::class.java)

        val router = Router.router(vertx)
        router.post("/payment").handler(self::save)
        router.get("/payment/:id").handler(self::findOne)

        return router
    }

    fun save(routingContext: RoutingContext) {
        log.info("processing /cashier/payment.")
        val cashierPayment = JACKSON.parse(routingContext.getBodyAsString(StringUtils.UTF_8),
                CashierPayment::class.java)

        cashierPaymentAsyncService.save(cashierPayment, Handler {

            if (it.succeeded()) {
                routingContext.response().end(JACKSON.convert(it.result()))
            } else {
                routingContext.fail(it.cause())
            }

        })
    }

    fun findOne(routingContext: RoutingContext) {
        val id = routingContext.request().getParam("id").toLong()
        cashierPaymentAsyncService.findById(id, Handler {
            if (it.succeeded()) {
                val record: Any = if (Objects.nonNull(it.result())) it.result() else mutableMapOf<String, Any>()
                routingContext.response().end(JACKSON.convert(record))
            } else {
                routingContext.fail(it.cause())
            }
        })
    }

    override fun setBeanFactory(beanFactory: BeanFactory) {
        self = beanFactory.getBean(this::class.java)
    }

}