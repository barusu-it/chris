package it.barusu.chris.channel.config

import org.springframework.data.mongodb.repository.MongoRepository

interface SecretConfigRepository : MongoRepository<SecretConfig, String>