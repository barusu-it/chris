package it.barusu.chris.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(

        @Id
        var id: String?,
        var firstName: String?

) {
    constructor() : this(null, null)
}