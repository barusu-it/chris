package it.barusu.chris

import it.barusu.chris.convertor.UserMapper
import it.barusu.chris.query.Blog
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory


class MapstructIntegrationTests {
    val log = LoggerFactory.getLogger(this.javaClass)

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