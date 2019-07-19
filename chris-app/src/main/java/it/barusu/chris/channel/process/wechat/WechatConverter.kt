package it.barusu.chris.channel.process.wechat

import com.google.common.base.Strings
import com.thoughtworks.xstream.XStream
import freemarker.ext.beans.BeansWrapperBuilder
import freemarker.template.Configuration
import freemarker.template.Template
import it.barusu.chris.channel.process.AbstractConverter
import it.barusu.chris.channel.process.Request
import it.barusu.chris.channel.process.bean.*
import it.barusu.chris.channel.process.wechat.mapper.ResponseMapper
import it.barusu.chris.common.TransactionStatus
import it.barusu.chris.util.DateUtils
import it.barusu.chris.util.IdUtils
import java.time.LocalDate
import java.util.*

class WechatConverter : AbstractConverter() {

    companion object {
        private const val TEMPLATE_ATTRIBUTE_REQUEST: String = "request"
        private const val CODE_SUCCESS: String = "SUCCESS"
        private const val CODE_ORDER_NOT_EXIST = "ORDERNOTEXIST"
        private val CODES_FAILED: Set<String> = setOf("CLOSED", "REVOKED")

    }


    private val transactionTemplate: Template = FREEMARKER_HELPER.configuration
            .getTemplate("WECHAT_Transaction.ftl")
    private val transactionQueryTemplate: Template = FREEMARKER_HELPER.configuration
            .getTemplate("WECHAT_TransactionQuery.ftl")

    @Suppress("SpellCheckingInspection")
    private val xstream: XStream = XStream()

    init {
        XStream.setupDefaultSecurity(xstream)
        xstream.allowTypes(arrayOf(ResponseMapper::class.java))
        xstream.processAnnotations(ResponseMapper::class.java)
        xstream.ignoreUnknownElements()
    }

    override fun from(transactionRequest: TransactionRequest): String {

        val wrapper = BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build()
        val templateHashModel = wrapper.staticModels
        val data = mapOf<String, Any>(
                TEMPLATE_ATTRIBUTE_REQUEST to transactionRequest,
                IdUtils::class.java.simpleName to templateHashModel[IdUtils::class.java.name],
                DateUtils::class.java.simpleName to templateHashModel[DateUtils::class.java.name])

        return render(transactionTemplate, data)
    }

    override fun toTransactionResponse(content: String, request: Request): TransactionResponse {
        val mapper = xstream.fromXML(content) as ResponseMapper

        val transactionCode = if (Objects.equals(CODE_SUCCESS, mapper.resultCode))
            mapper.resultCode else mapper.errorCode
        val transactionStatus = if (Objects.equals(CODE_SUCCESS, mapper.resultCode))
            TransactionStatus.PROCESSING else TransactionStatus.FAILED

        val response = TransactionResponse()
        response.code = mapper.code
        response.message = mapper.message
        response.transaction = Transaction(
                status = transactionStatus,
                code = transactionCode,
                message = mapper.errorMessage,
                channelPrepayNo = mapper.prepayId,
                paymentCode = mapper.codeUrl,
                settlementDate = if (Objects.nonNull(mapper.finishedTime))
                    LocalDate.parse(mapper.finishedTime, DateUtils.DATE) else null)

        return response
    }

    override fun from(transactionQueryRequest: TransactionQueryRequest): String {
        val wrapper = BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build()
        val templateHashModel = wrapper.staticModels
        val data = mapOf<String, Any>(
                TEMPLATE_ATTRIBUTE_REQUEST to transactionQueryRequest,
                IdUtils::class.java.simpleName to templateHashModel[IdUtils::class.java.name])

        return render(transactionQueryTemplate, data)
    }

    override fun toTransactionQueryResponse(content: String, request: Request): TransactionQueryResponse {
        val mapper = xstream.fromXML(content) as ResponseMapper

        val response = TransactionQueryResponse()
        response.code = mapper.code
        response.message = mapper.message
        response.transaction = Transaction(
                status = if (Objects.equals(CODE_SUCCESS, mapper.code) || mapper.resultCode!!.endsWith(CODE_SUCCESS)) {
                    when {
                        Objects.equals(CODE_SUCCESS, mapper.tradeState) -> TransactionStatus.SUCCEED
                        Objects.equals(CODE_ORDER_NOT_EXIST, mapper.errorCode) -> TransactionStatus.FAILED
                        CODES_FAILED.contains(mapper.tradeState) -> TransactionStatus.FAILED
                        else -> TransactionStatus.PROCESSING
                    }
                } else {
                    TransactionStatus.PROCESSING
                },
                code = if (Strings.isNullOrEmpty(mapper.errorCode))
                    mapper.resultCode else mapper.errorCode,
                message = mapper.errorMessage,
                settlementDate = if (Objects.nonNull(mapper.finishedTime))
                    LocalDate.parse(mapper.finishedTime, DateUtils.DATE) else null,
                channelPrepayNo = mapper.prepayId
        )

        return response
    }
}