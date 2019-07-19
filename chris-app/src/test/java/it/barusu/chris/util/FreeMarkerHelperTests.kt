package it.barusu.chris.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class FreeMarkerHelperTests {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @DisplayName("test freemarker")
    @Test
    fun testFreeMarker() {
        val helper = FreeMarkerHelper(arrayOf("/templates"))
        log.info("create freemarker helper.")
        assertThat(helper).isNotNull
    }

}