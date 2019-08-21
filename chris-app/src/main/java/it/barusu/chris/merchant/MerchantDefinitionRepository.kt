package it.barusu.chris.merchant

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MerchantDefinitionRepository : JpaRepository<MerchantDefinition, Long> {
    fun findByMerchantNo(merchantNo: String): Optional<MerchantDefinition>
}