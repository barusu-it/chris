package it.barusu.chris.util.json

interface JsonProvider {
    fun <T> parse(text: String, targetType: Class<T>): T
}