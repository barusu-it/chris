package it.barusu.chris

import io.vertx.core.AbstractVerticle
import io.vertx.core.CompositeFuture
import io.vertx.core.Promise
import io.vertx.serviceproxy.ServiceBinder
import it.barusu.chris.cashier.CashierPaymentAsyncService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
@Scope(SCOPE_PROTOTYPE)
class SpringWorker : AbstractVerticle() {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Resource
    private lateinit var cashierPaymentAsyncService: CashierPaymentAsyncService

    override fun start(startPromise: Promise<Void>?) {
        val binder = ServiceBinder(vertx)



        val promise = Promise.promise<Void>()
        binder
//                .setAddress(CashierPaymentAsyncService.ADDRESS)
                .setAddress(CashierPaymentAsyncService::class.java.name)
                .register(CashierPaymentAsyncService::class.java, cashierPaymentAsyncService)
                .completionHandler(promise)

        CompositeFuture.all(listOf(promise.future())).setHandler {

            if (it.succeeded()) {
                log.info("Async services registered.")
                startPromise!!.complete()
            } else {
                log.error(it.cause().message, it.cause())
                startPromise!!.fail(it.cause())
            }
        }


    }
}