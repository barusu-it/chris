package it.barusu.chris.util

import java.nio.charset.StandardCharsets

class StringUtils {
    companion object {
        @JvmStatic
        val UTF_8: String = StandardCharsets.UTF_8.name()

        const val EMPTY: String = ""
        const val DASH: String = "-"
        const val DATE: String = "yyyyMMdd"
        const val DATETIME: String = "yyyyMMddHHmmss"

    }


}