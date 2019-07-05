package it.barusu.chris

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import redis.embedded.RedisServer

@DataRedisTest
@ExtendWith(SpringExtension::class)
class RedisSpringIntegrationTests {

    private val log = LoggerFactory.getLogger(this.javaClass)

    private lateinit var redisServer: RedisServer

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    @BeforeEach
    fun beforeAll() {
        log.info("redis server starting.")
        redisServer = RedisServer()
        redisServer.start()
    }

    @AfterEach
    fun afterAll() {
        log.info("redis server stopping.")
        redisServer.stop()
    }

    @DisplayName("test redis")
    @Test
    fun testRedis() {
        val key = "test-key"
        val value1 = "test-value1"
        val value2 = "test-value2"

        redisTemplate.opsForValue().set(key, value1)

        var strValue = redisTemplate.opsForValue().get(key)
        log.info("first get value: $strValue")

        assertThat(strValue).isEqualTo(value1)

        redisTemplate.opsForValue().set(key, value2)

        strValue = redisTemplate.opsForValue().get(key)
        log.info("second get value: $strValue")

        assertThat(strValue).isEqualTo(value2)

    }
}