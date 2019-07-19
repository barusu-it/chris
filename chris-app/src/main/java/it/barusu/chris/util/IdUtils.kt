package it.barusu.chris.util

import java.util.*

class IdUtils {
    companion object {

        @JvmStatic
        fun uuid(): String = UUID.randomUUID().toString()

        @JvmStatic
        fun uuidWithoutDash(): String = uuid().replace(StringUtils.DASH, StringUtils.EMPTY)
    }
}