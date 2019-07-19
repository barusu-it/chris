package it.barusu.chris.util.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class JacksonProvider : JsonProvider {

    companion object {
        @JvmStatic
        val DEFAULT_OBJECT_MAPPER: ObjectMapper = initObjectMapper()

        private fun initObjectMapper(): ObjectMapper {
            val objectMapper = ObjectMapper()
            objectMapper.registerModule(Jdk8Module())
            objectMapper.registerModule(JavaTimeModule())
//            objectMapper.registerModule(GuavaModule())
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)

            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

            return objectMapper

        }
    }

    override fun <T> parse(text: String, targetType: Class<T>): T {
        return DEFAULT_OBJECT_MAPPER.readValue(text, targetType)
    }

}