package it.barusu.chris.cashier.query

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CashierPaymentGlobalConfigManager(val repository: CashierPaymentGlobalConfigRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional
    fun save(record: CashierPaymentGlobalConfig): CashierPaymentGlobalConfig {
        return repository.save(record)
    }

    @Transactional
    fun delete(record: CashierPaymentGlobalConfig) {
        repository.delete(record)
    }

    fun findByExample(params: CashierPaymentGlobalConfig): MutableList<CashierPaymentGlobalConfig> {
        return repository.findAll(Example.of(params))
    }

    fun fundById(id: String): Optional<CashierPaymentGlobalConfig> {
        return repository.findById(id)
    }


}