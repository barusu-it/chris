package it.barusu.chris.channel.config

import it.barusu.chris.channel.AbstractChannelTests
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
class DefinitionConfigRepositoryTests : AbstractChannelTests() {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    private lateinit var definitionConfigRepository: DefinitionConfigRepository

    @DisplayName("test save definition config")
    @Test
    fun testSaveDefinitionConfig() {
        var config = DefinitionConfig(
                channelNo = "WECHAT_DEFAULT",
                channelType = ChannelType.WECHAT,
                name = "微信",
                description = "微信默认渠道")

        config = definitionConfigRepository.save(config)

        log.info("save definition config: $config")

        val query = definitionConfigRepository.findByChannelNo(config.channelNo!!).get()

        assertThat(query).isEqualTo(config)


    }
}