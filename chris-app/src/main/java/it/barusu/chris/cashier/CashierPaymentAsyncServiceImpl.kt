package it.barusu.chris.cashier

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import it.barusu.chris.cashier.query.CashierPayment
import it.barusu.chris.cashier.query.CashierPaymentManager
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class CashierPaymentAsyncServiceImpl :CashierPaymentAsyncService {

    @Resource
    lateinit var cashierPaymentManager: CashierPaymentManager

    override fun save(record: CashierPayment, handler: Handler<AsyncResult<CashierPayment>>) {
        val cashierPayment = cashierPaymentManager.save(record)
        Future.succeededFuture(cashierPayment).setHandler(handler)
    }

    override fun findById(id: Long, handler: Handler<AsyncResult<CashierPayment>>) {
        val optional = cashierPaymentManager.fundById(id)
        Future.succeededFuture(optional.orElse(null)).setHandler(handler)
    }

}