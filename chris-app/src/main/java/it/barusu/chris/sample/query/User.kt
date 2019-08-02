package it.barusu.chris.sample.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
        @Id
        var id: String? = null,
        var firstName: String? = null
)