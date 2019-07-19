package it.barusu.chris.channel.process.bean

import it.barusu.chris.common.TransactionStatus
import it.barusu.chris.common.TransactionType
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class Transaction(
        var transactionNo: String? = null,
        var serialNo: String? = null,
        var transactionType: TransactionType? = null,
        var channelNo: String? = null,
        var channelTransactionNo: String? = null,
        var channelPrepayNo: String? = null,
        var bankAccount: BankAccount? = null,
        var amount: BigDecimal? = null,
        var expiredTime: LocalDateTime? = null,
        var completedTime: LocalDateTime? = null,
        var settlementDate: LocalDate? = null,
        var status: TransactionStatus? = null,
        var description: String? = null,
        var paymentCode: String? = null, // 支付码(二维码)
        var code: String? = null,
        var message: String? = null)