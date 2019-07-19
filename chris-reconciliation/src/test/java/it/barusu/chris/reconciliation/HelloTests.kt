package it.barusu.chris.reconciliation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HelloTests {

    @DisplayName("test hi")
    @Test
    fun testHi() {
        assertThat(Hello().hi()).isEqualTo("hello world!")
    }

}