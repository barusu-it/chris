package it.barusu.chris.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class IdUtilsTests {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @DisplayName("test uuid without dash")
    @Test
    fun testUuidWithoutDash() {
        val uuid = IdUtils.uuidWithoutDash()
        log.info("uuid without dash: $uuid")
        assertThat(uuid).doesNotContain(StringUtils.DASH)
    }

}