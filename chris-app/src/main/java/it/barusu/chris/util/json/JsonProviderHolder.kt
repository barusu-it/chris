package it.barusu.chris.util.json

interface JsonProviderHolder {

    companion object {
        @JvmStatic
        val JACKSON = JacksonProvider()
    }
}