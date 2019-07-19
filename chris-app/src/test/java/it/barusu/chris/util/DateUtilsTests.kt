package it.barusu.chris.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class DateUtilsTests {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @DisplayName("test date & datetime")
    @Test
    fun testDateAndDateTime() {
        val datetime = LocalDateTime.of(2019, 7, 3, 11, 23, 44)
        log.info("datetime: $datetime")
        assertThat(datetime.format(DateUtils.DATE)).isEqualTo("20190703")
        assertThat(datetime.format(DateUtils.DATETIME)).isEqualTo("20190703112344")
    }
}
