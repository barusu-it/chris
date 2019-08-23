package it.barusu.chris.cashier

import it.barusu.chris.cashier.query.CashierGroupManager
import it.barusu.chris.cashier.query.CashierPaymentGlobalConfigManager
import it.barusu.chris.cashier.query.CashierPaymentManager
import it.barusu.chris.cashier.query.CashierPaymentRuleManager
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.stereotype.Component

@Component
class CashierService(val cashierPaymentManager: CashierPaymentManager,
                     val cashierGroupManager: CashierGroupManager,
                     val cashierPaymentGlobalConfigManager: CashierPaymentGlobalConfigManager,
                     val cashierPaymentRuleManager: CashierPaymentRuleManager) : BeanFactoryAware {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    protected lateinit var self: CashierService

    override fun setBeanFactory(beanFactory: BeanFactory) {
        self = beanFactory.getBean(this::class.java)
    }


}