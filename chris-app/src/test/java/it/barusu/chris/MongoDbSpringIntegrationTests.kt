package it.barusu.chris

import it.barusu.chris.query.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@DataMongoTest
@ExtendWith(SpringExtension::class)
class MongoDbSpringIntegrationTests {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @DisplayName("test mongo")
    @Test
    fun testMongo() {
        val user = User(
                id = UUID.randomUUID().toString().replace("-", ""),
                firstName = "jessie"
        )

        mongoTemplate.save(user)

        assertThat(mongoTemplate.findAll(User::class.java, "users"))
                .extracting("id")
                .containsOnly(user.id)

    }

}
