package it.barusu.chris

import it.barusu.chris.sample.convertor.UserMapper
import it.barusu.chris.sample.convertor.Blog
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory


class MapstructIntegrationTests {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @DisplayName("test mapstruct")
    @Test
    fun testMapstruct() {
        val blog = Blog(title = "hello")
        val mapper = Mappers.getMapper(UserMapper::class.java)
        val user = mapper.from(blog)
        log.info("user: $user")
        assertThat(user.firstName).isEqualTo(blog.title)
    }
}