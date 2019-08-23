package it.barusu.chris.cashier.query

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CashierGroupManager(val repository: CashierGroupRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional
    fun save(record: CashierGroup): CashierGroup {
        return repository.save(record)
    }

    @Transactional
    fun delete(record: CashierGroup) {
        repository.delete(record)
    }

    fun findByMerchantId(merchantId: Long): MutableList<CashierGroup> {
        val params = CashierGroup(merchantId = merchantId)
        return repository.findAll(Example.of(params))
    }

    fun fundById(id: Long): CashierGroup {
        return repository.getOne(id)
    }

}