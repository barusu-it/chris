package it.barusu.chris.util

import org.apache.commons.codec.binary.Hex
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class SecurityUtilsTests {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @DisplayName("test digest")
    @Test
    fun testDigest() {
        val data = "123"
        val bytes = SecurityUtils.digest(SecurityUtils.MD5, data.toByteArray())
        val digestedString = Hex.encodeHexString(bytes)
        log.info("digested string: $digestedString")
        @Suppress("UsePropertyAccessSyntax")
        assertThat(digestedString)
                .isNotNull()
                .isEqualTo("202cb962ac59075b964b07152d234b70")

    }
}