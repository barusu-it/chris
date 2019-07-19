package it.barusu.chris.util

import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {

        @JvmStatic
        val DATE = of(StringUtils.DATE)

        @JvmStatic
        val DATETIME = of(StringUtils.DATETIME)

        @JvmStatic
        fun of(format: String): DateTimeFormatter = DateTimeFormatter.ofPattern(format)

    }

}