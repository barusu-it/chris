package it.barusu.chris.util

import java.nio.charset.StandardCharsets

class StringUtils {
    companion object {
        @JvmStatic
        val UTF_8: String = StandardCharsets.UTF_8.name()

        const val EQUAL_SIGN: String = "="
        const val AMPERSAND: String = "&"
        const val EMPTY: String = ""
        const val DASH: String = "-"
        const val COLON: String = ":"
        const val DATE: String = "yyyyMMdd"
        const val DATETIME: String = "yyyyMMddHHmmss"

        @JvmStatic
        fun pair(data: Map<String, String>): String {
            return data.entries.stream()
                    .map { it.key + EQUAL_SIGN + it.value }
                    .reduce { t: String?, u: String? -> t + AMPERSAND + u }
                    .orElse(AMPERSAND)
        }

    }


}