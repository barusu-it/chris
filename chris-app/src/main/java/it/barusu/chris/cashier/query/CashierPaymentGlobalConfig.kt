package it.barusu.chris.cashier.query

import it.barusu.chris.common.CashierType
import it.barusu.chris.common.ChannelType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

/**
 * default config for cashier payment if name or logo is not defined in cashier payment.
 */
@Document(collection = "cs_cashier_payment_global_config")
@CompoundIndexes(value = [CompoundIndex(def = "{'cashierType': 1, 'channelType': 1}")])
data class CashierPaymentGlobalConfig(
        @Id
        var id: String,
        var cashierType: CashierType? = null,
        var channelType: ChannelType? = null,
        var name: String? = null,
        var logo: String? = null

)