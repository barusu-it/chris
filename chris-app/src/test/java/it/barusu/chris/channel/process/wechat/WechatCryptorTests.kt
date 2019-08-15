package it.barusu.chris.channel.process.wechat

import it.barusu.chris.channel.AbstractChannelTests
import it.barusu.chris.channel.config.SecretConfig
import it.barusu.chris.channel.process.bean.TransactionRequest
import it.barusu.chris.common.ChannelType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WechatCryptorTests : AbstractChannelTests() {

    @Suppress("SpellCheckingInspection")
    private val cryptor: WechatCryptor = WechatCryptor()
    private val config: SecretConfig = getConfig(ChannelType.WECHAT, DEFAULT_CHANNEL_NO)

    @DisplayName("test sign")
    @Test
    fun testSign() {
        val content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><appid>wx99bcf174724d0ae0</appid><mch_id>1251462001</mch_id><nonce_str>52dfdd18186a42cabb96f98882c4e69d</nonce_str><out_trade_no>c91592e61b2f4fe98bb9af530b2831e4</out_trade_no><product_id>c91592e61b2f4fe98bb9af530b2831e4</product_id><sign_type>MD5</sign_type><body>test transaction</body><detail>test transaction</detail><notify_url>http://127.0.0.1</notify_url><fee_type>CNY</fee_type><total_fee>100</total_fee><time_start>20190430153204</time_start><time_expire>20190430163204</time_expire><trade_type>NATIVE</trade_type><sign></sign></xml>"
        val request = TransactionRequest()
        request.config = config

        val sign = cryptor.sign(content, request)

        @Suppress("UsePropertyAccessSyntax")
        assertThat(sign).isNotEmpty().contains("B42C02F1B1B6ADF60F4392569CDE446E")
    }

    @DisplayName("test verify")
    @Test
    fun testVerify() {
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

        val request = TransactionRequest()
        request.config = config
        cryptor.verify(content, request)
    }
}
