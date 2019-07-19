package it.barusu.chris.channel.process

interface Converter {
    fun writeTo(request: Request): String
    fun readFrom(content: String, request: Request): Response
}