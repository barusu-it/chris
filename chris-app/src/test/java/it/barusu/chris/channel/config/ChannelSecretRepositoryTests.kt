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
class ChannelSecretRepositoryTests : AbstractChannelTests() {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    lateinit var channelSecretRepository: ChannelSecretRepository

    @DisplayName("test save secret")
    @Test
    fun testSaveSecretConfig() {
        val secret: ChannelSecret = getSecret(ChannelType.WECHAT, DEFAULT_CHANNEL_NO)

        channelSecretRepository.save(secret)
        log.info("save secret config: $secret")

        val query = channelSecretRepository.findById(secret.channelNo ?: DEFAULT_CHANNEL_NO)
        assertThat(query.get()).isEqualTo(secret)
    }
}