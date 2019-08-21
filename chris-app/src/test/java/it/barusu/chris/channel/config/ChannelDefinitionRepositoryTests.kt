package it.barusu.chris.channel.config

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
class ChannelDefinitionRepositoryTests {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    private lateinit var channelDefinitionRepository: ChannelDefinitionRepository

    @DisplayName("test save definition")
    @Test
    fun testSaveDefinition() {
        var definition = ChannelDefinition(
                channelNo = "WECHAT_DEFAULT_01",
                channelType = ChannelType.WECHAT,
                name = "微信",
                description = "微信默认渠道")

        definition = channelDefinitionRepository.save(definition)

        log.info("save definition: $definition")

        val query = channelDefinitionRepository.findByChannelNo(definition.channelNo!!).get()

        assertThat(query).isEqualTo(definition)


    }
}