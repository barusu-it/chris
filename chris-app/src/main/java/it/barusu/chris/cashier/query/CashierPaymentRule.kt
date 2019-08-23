package it.barusu.chris.cashier.query

import it.barusu.chris.common.CashierType
import it.barusu.chris.common.ChannelType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cs_cashier_payment_rule")
@CompoundIndexes(value = [CompoundIndex(def = "{'merchantId': 1, 'cashierType': 1}, 'channelType': 1}")])
data class CashierPaymentRule(
        @Id
        var id: String,

        @Indexed(unique = true)
        var paymentId: Long,

        var merchantId: Long? = null,
        var cashierType: CashierType? = null,
        var channelType: ChannelType? = null,
        var rules: String? = null
)