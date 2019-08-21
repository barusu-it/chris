package it.barusu.chris.merchant

import it.barusu.chris.channel.config.ChannelDefinition
import it.barusu.chris.channel.config.ChannelDefinitionRepository
import it.barusu.chris.common.ChannelType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class MerchantDefinitionRepositoryTests {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    private lateinit var merchantDefinitionRepository: MerchantDefinitionRepository

    @Autowired
    private lateinit var channelDefinitionRepository: ChannelDefinitionRepository


    @DisplayName("test save definition")
    @Test
    fun testSaveDefinition() {
        var merchant = MerchantDefinition(merchantNo = "test_merchant_01", name = "测试商户01", description = "DESC01")

        merchant = merchantDefinitionRepository.save(merchant)

        log.info("save merchant: $merchant")

        val query = merchantDefinitionRepository.findByMerchantNo(merchant.merchantNo!!).get()

        @Suppress("UsePropertyAccessSyntax")
        assertThat(query).isNotNull()
        assertThat(query.name).isEqualTo(merchant.name)

        val channelNo = "WECHAT_DEFAULT_01"

        val channelOptional = channelDefinitionRepository.findByChannelNo(channelNo)

        @Suppress("UsePropertyAccessSyntax")
        val channel: ChannelDefinition = if (channelOptional.isPresent()) {
            channelOptional.get()
        } else {
            val origin = ChannelDefinition(
                    channelNo = "WECHAT_DEFAULT_01",
                    channelType = ChannelType.WECHAT,
                    name = "微信",
                    description = "微信默认渠道")
            channelDefinitionRepository.save(origin)
        }

        merchant.channels.add(channel)

        merchant = merchantDefinitionRepository.save(merchant)

        val channelQuery = channelDefinitionRepository.findByChannelNo(channelNo).get()
        val merchants = channelQuery.merchants

        @Suppress("UsePropertyAccessSyntax")
        assertThat(merchants).isNotEmpty()
        assertThat(merchants.elementAt(0)).isEqualTo(merchant)


    }
}
