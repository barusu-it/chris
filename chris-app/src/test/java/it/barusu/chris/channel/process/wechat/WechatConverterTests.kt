package it.barusu.chris.channel.process.wechat

import it.barusu.chris.channel.process.SecretConfig
import it.barusu.chris.channel.process.bean.Transaction
import it.barusu.chris.channel.process.bean.TransactionQueryRequest
import it.barusu.chris.channel.process.bean.TransactionRequest
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

class WechatConverterTests : AbstractChannelTests() {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    private val converter: WechatConverter = WechatConverter()
    private var config: SecretConfig = getConfig(ChannelType.WECHAT, DEFAULT_CHANNEL_NO)

    @DisplayName("test from transaction")
    @Test
    fun testFromTransaction() {
        val request = TransactionRequest(IdUtils.uuid())
        request.config = config
        request.transactionType = TransactionType.WITHHOLD
        request.transaction = Transaction(
                transactionNo = IdUtils.uuidWithoutDash(),
                description = "test transaction",
                amount = BigDecimal("1"),
                expiredTime = LocalDateTime.now().plusHours(1L))

        val requestContent = converter.from(request)
        log.info("from transaction content: $requestContent")

        @Suppress("UsePropertyAccessSyntax")
        // if using isNotNull, it will throw exception, so use isNotNull() here
        assertThat(requestContent)
                .isNotNull()
                .contains("appid")
                .contains("mch_id")
    }

    @DisplayName("test to transaction")
    @Test
    fun testToTransaction() {
        val content = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx99bcf174724d0ae0]]></appid>\n" +
                "<mch_id><![CDATA[1251462001]]></mch_id>\n" +
                "<nonce_str><![CDATA[lbMeN1BDlecy00Rh]]></nonce_str>\n" +
                "<sign><![CDATA[0774DB3F7319034CE1B78F966820DEE5]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<prepay_id><![CDATA[wx30165715577881d4d4de0c5d2282184633]]></prepay_id>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<code_url><![CDATA[weixin://wxpay/bizpayurl?pr=dSLgwqI]]></code_url>\n" +
                "</xml>"

        val request = TransactionRequest(IdUtils.uuidWithoutDash())
        request.config = config

        val response = converter.toTransactionResponse(content, request)
        log.info("to transaction response: $response")

        @Suppress("UsePropertyAccessSyntax")
        assertThat(response).isNotNull()
        assertThat(response.transaction?.status).isEqualTo(TransactionStatus.PROCESSING)
        assertThat(response.transaction?.channelPrepayNo).isEqualTo("wx30165715577881d4d4de0c5d2282184633")
    }

    @DisplayName("test from transaction query")
    @Test
    fun testFromTransactionQuery() {
        val request = TransactionQueryRequest(orderNo = IdUtils.uuid())
        request.config = config
        request.transaction = Transaction(
                transactionNo = "a14f9fc9cfb34451b930b1530eb8ed1c",
                transactionType = TransactionType.WITHHOLD)

        val requestContent = converter.from(request)

        log.info("from transaction query content: $requestContent")

        @Suppress("UsePropertyAccessSyntax")
        assertThat(requestContent)
                .isNotNull()
                .contains("appid")
                .contains("mch_id")
    }

    @DisplayName("test to transaction query")
    @Test
    fun testToTransactionQuery() {
        val content = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx99bcf174724d0ae0]]></appid>\n" +
                "<mch_id><![CDATA[1251462001]]></mch_id>\n" +
                "<nonce_str><![CDATA[lbMeN1BDlecy00Rh]]></nonce_str>\n" +
                "<sign><![CDATA[0774DB3F7319034CE1B78F966820DEE5]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<prepay_id><![CDATA[wx30165715577881d4d4de0c5d2282184633]]></prepay_id>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<code_url><![CDATA[weixin://wxpay/bizpayurl?pr=dSLgwqI]]></code_url>\n" +
                "</xml>"

        val request = TransactionQueryRequest()
        request.config = config

        val response = converter.toTransactionQueryResponse(content, request)
        log.info("to transaction query response: $response")

        @Suppress("UsePropertyAccessSyntax")
        assertThat(response).isNotNull()
        assertThat(response.transaction?.status).isEqualTo(TransactionStatus.PROCESSING)
        assertThat(response.transaction?.channelPrepayNo).isEqualTo("wx30165715577881d4d4de0c5d2282184633")
    }



}