package it.barusu.chris.cashier

import io.vertx.codegen.annotations.GenIgnore
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import it.barusu.chris.cashier.query.CashierPayment

@ProxyGen
interface CashierPaymentAsyncService {


    fun save(record: CashierPayment, handler: Handler<AsyncResult<CashierPayment>>)

    fun findById(id: Long, handler: Handler<AsyncResult<CashierPayment>>)

    // vertx codegen lang kotlin does not support generate companion object
//    companion object {
//        @GenIgnore
//        @JvmStatic
//        val ADDRESS: String = CashierPaymentAsyncService::class.java.name
//    }


}