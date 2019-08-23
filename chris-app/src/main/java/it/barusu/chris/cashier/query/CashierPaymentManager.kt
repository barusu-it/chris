package it.barusu.chris.cashier.query

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CashierPaymentManager(val repository: CashierPaymentRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional
    fun save(record: CashierPayment): CashierPayment {
        return repository.save(record)
    }

    @Transactional
    fun delete(record: CashierPayment) {
        repository.delete(record)
    }

    fun findByMerchantId(merchantId: Long): MutableList<CashierPayment> {
        val params = CashierPayment(merchantId = merchantId)
        return repository.findAll(Example.of(params))
    }

    fun fundById(id: Long): CashierPayment {
        return repository.getOne(id)
    }
}