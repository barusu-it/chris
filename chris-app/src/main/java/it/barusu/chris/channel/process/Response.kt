package it.barusu.chris.channel.process

import java.time.LocalDateTime

abstract class Response {
    var code: String? = null
    var message: String? = null
    var content: String? = null
    val createdTime: LocalDateTime = LocalDateTime.now()
}