package it.barusu.chris

import it.barusu.chris.query.Blog
import it.barusu.chris.query.BlogRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
class H2SpringIntegrationTests {
    val log = LoggerFactory.getLogger(this.javaClass)!!

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