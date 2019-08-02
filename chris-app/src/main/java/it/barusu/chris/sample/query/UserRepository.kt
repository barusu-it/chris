package it.barusu.chris.sample.query

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>