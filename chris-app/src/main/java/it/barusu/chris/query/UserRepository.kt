package it.barusu.chris.query

import it.barusu.chris.query.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>