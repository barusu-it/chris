package it.barusu.chris.channel.process

import freemarker.template.Template
import it.barusu.chris.channel.process.bean.*
import it.barusu.chris.common.RequestType
import it.barusu.chris.util.FreeMarkerHelper

abstract class AbstractConverter : Converter {

    companion object {
        @JvmStatic
        val FREEMARKER_HELPER = FreeMarkerHelper(arrayOf("/templates/wechat"))
    }

    fun render(template: Template, data: Map<String, Any>): String = FREEMARKER_HELPER.render(template, data)

    override fun writeTo(request: Request): String =
            when (request.type) {
                RequestType.TRANSACTION -> from(request as TransactionRequest)
                RequestType.TRANSACTION_QUERY -> from(request as TransactionQueryRequest)
                RequestType.TRANSACTION_NOTIFICATION -> from(request as TransactionNotificationRequest)
            }

    override fun readFrom(content: String, request: Request): Response =
            when (request.type) {
                RequestType.TRANSACTION -> toTransactionResponse(content, request)
                RequestType.TRANSACTION_QUERY -> toTransactionQueryResponse(content, request)
                RequestType.TRANSACTION_NOTIFICATION -> toTransactionNotificationResponse(content, request)
            }

    open fun from(transactionRequest: TransactionRequest): String {
        throw UnsupportedOperationException("Transaction is not supported.")
    }

    open fun from(transactionQueryRequest: TransactionQueryRequest): String {
        throw UnsupportedOperationException("Transaction query is not supported.")
    }

    open fun from(transactionNotificationRequest: TransactionNotificationRequest): String {
        throw UnsupportedOperationException("Transaction notification is not supported.")
    }

    open fun toTransactionResponse(content: String, request: Request): TransactionResponse {
        throw UnsupportedOperationException("Transaction is not supported.")
    }

    open fun toTransactionQueryResponse(content: String, request: Request): TransactionQueryResponse {
        throw UnsupportedOperationException("Transaction query is not supported.")
    }

    open fun toTransactionNotificationResponse(content: String, request: Request): TransactionNotificationResponse {
        throw UnsupportedOperationException("Transaction notification is not supported.")
    }


}