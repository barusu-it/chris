package it.barusu.chris.cashier.query

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CashierPaymentRuleManager(val repository: CashierPaymentRuleRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional
    fun save(record: CashierPaymentRule): CashierPaymentRule {
        return repository.save(record)
    }

    @Transactional
    fun delete(record: CashierPaymentRule) {
        repository.delete(record)
    }

    fun findByExample(params: CashierPaymentRule): MutableList<CashierPaymentRule> {
        return repository.findAll(Example.of(params))
    }

    fun fundById(id: String): Optional<CashierPaymentRule> {
        return repository.findById(id)
    }
}
