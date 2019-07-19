package it.barusu.chris

import it.barusu.chris.query.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@DataMongoTest
@ExtendWith(SpringExtension::class)
class MongoDbSpringIntegrationTests {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @DisplayName("test embed mongo")
    @Test
    fun testMongo() {
        val user = User(
                id = UUID.randomUUID().toString().replace("-", ""),
                firstName = "jessie"
        )

        mongoTemplate.save(user)
        log.info("save user: $user")

        assertThat(mongoTemplate.findAll(User::class.java, "users"))
                .extracting("id")
                .containsOnly(user.id)

    }

}
