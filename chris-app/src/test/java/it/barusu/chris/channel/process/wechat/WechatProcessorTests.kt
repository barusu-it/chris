package it.barusu.chris.channel.process.wechat

import it.barusu.chris.channel.AbstractChannelTests
import it.barusu.chris.channel.config.SecretConfig
import it.barusu.chris.channel.process.bean.*
import it.barusu.chris.channel.process.wechat.WechatConverter.Companion.CODE_SUCCESS
import it.barusu.chris.common.ChannelType
import it.barusu.chris.common.TransactionStatus
import it.barusu.chris.common.TransactionType
import it.barusu.chris.util.IdUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.time.LocalDateTime

class WechatProcessorTests : AbstractChannelTests() {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    private val processor: WechatProcessor = WechatProcessor(httpClient(), WechatConverter(), WechatCryptor())
    private val config: SecretConfig = getConfig(ChannelType.WECHAT, DEFAULT_CHANNEL_NO)

    @DisplayName("test transaction")
    @Test
    fun testTransaction() {
        val request = TransactionRequest()
        request.config = config
        request.orderNo = IdUtils.uuid()
        request.transactionType = TransactionType.WITHHOLD
        request.transaction = Transaction(
                transactionNo = IdUtils.uuidWithoutDash(),
                description = "test transaction",
                amount = BigDecimal.valueOf(1),
                expiredTime = LocalDateTime.now().plusHours(1L))
        val response: TransactionResponse = processor.execute(request)

        log.info("response: $response")

        @Suppress("UsePropertyAccessSyntax")
        assertThat(response).isNotNull()
        assertThat(response.transaction!!.code).isEqualTo(CODE_SUCCESS)
        assertThat(response.transaction!!.status).isEqualTo(TransactionStatus.PROCESSING)
    }

    @DisplayName("test transaction query")
    @Test
    fun testTransactionQuery() {

        val request = TransactionQueryRequest()
        request.config = config
        request.transactionType = TransactionType.WITHHOLD
        request.transaction = Transaction(
                transactionNo = "a14f9fc9cfb34451b930b1530eb8ed1c")
        val response: TransactionQueryResponse = processor.execute(request)

        log.info("response: $response")

        @Suppress("UsePropertyAccessSyntax")
        assertThat(response).isNotNull()
        assertThat(response.transaction!!.code).isEqualTo(CODE_SUCCESS)
        assertThat(response.transaction!!.status).isEqualTo(TransactionStatus.FAILED)
    }


}