package it.barusu.chris.channel.process.bean

import it.barusu.chris.channel.process.Request
import it.barusu.chris.channel.process.Response
import it.barusu.chris.common.RequestType

data class TransactionRequest(var orderNo: String? = null,
                              var transaction: Transaction? = null)
    : Request(type = RequestType.TRANSACTION)

data class TransactionResponse(var orderNo: String? = null,
                               var transaction: Transaction? = null) : Response()

data class TransactionQueryRequest(var orderNo: String? = null,
                                   var transaction: Transaction? = null)
    : Request(type = RequestType.TRANSACTION_QUERY)

data class TransactionQueryResponse(var orderNo: String? = null,
                                    var transaction: Transaction? = null) : Response()

data class TransactionNotificationRequest(var transactionNo: String? = null,
                                          var transaction: Transaction? = null)
    : Request(type = RequestType.TRANSACTION_NOTIFICATION)

data class TransactionNotificationResponse(var transactionNo: String? = null,
                                           var transaction: Transaction? = null) : Response()