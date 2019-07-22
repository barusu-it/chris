package it.barusu.chris.util.json

class JsonProviderHolder {

    companion object {
        @JvmStatic
        val JACKSON = JacksonProvider()
    }
}