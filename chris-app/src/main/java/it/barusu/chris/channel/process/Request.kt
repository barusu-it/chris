package it.barusu.chris.channel.process

import it.barusu.chris.common.Phase
import it.barusu.chris.common.RequestType
import it.barusu.chris.common.TransactionType
import java.time.LocalDateTime

abstract class Request(val type: RequestType) {
    val createdTime: LocalDateTime = LocalDateTime.now()
    var content: String? = null
    lateinit var transactionType: TransactionType
    lateinit var phase: Phase
    lateinit var config: SecretConfig
}