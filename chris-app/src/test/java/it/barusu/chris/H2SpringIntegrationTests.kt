package it.barusu.chris

import it.barusu.chris.sample.query.Blog
import it.barusu.chris.sample.query.BlogRepository
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
class H2SpringIntegrationTests {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    lateinit var blogRepository: BlogRepository

    @DisplayName("test h2")
    @Test
    fun testH2() {
        val blog = Blog(title = "test title")

        blogRepository.save(blog)

        val list = blogRepository.findAll()
        log.info("list: $list")
        assertThat(blogRepository.findAll())
                .extracting("title")
                .containsOnly("test title")

    }

}