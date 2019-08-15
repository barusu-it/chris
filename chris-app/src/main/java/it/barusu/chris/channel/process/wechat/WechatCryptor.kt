package it.barusu.chris.channel.process.wechat

import com.thoughtworks.xstream.XStream
import it.barusu.chris.channel.process.Request
import it.barusu.chris.channel.process.wechat.WechatConverter.Companion.CODE_SUCCESS
import it.barusu.chris.common.ApiException
import it.barusu.chris.util.SecurityUtils
import it.barusu.chris.util.StringUtils
import it.barusu.chris.util.StringUtils.Companion.AMPERSAND
import it.barusu.chris.util.StringUtils.Companion.EQUAL_SIGN
import it.barusu.chris.util.xstream.NestedMapConverter
import it.barusu.chris.util.xstream.OrdinalType
import org.apache.commons.codec.binary.Hex
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

class WechatCryptor {
    companion object {
        @JvmStatic
        private val log: Logger = LoggerFactory.getLogger(this::class.java)

        @JvmStatic
        private val XML_NODE_ROOT: String = "xml"

        @JvmStatic
        private val XML_NODE_SIGN: String = "sign"

        @JvmStatic
        private val XML_NODE_CODE: String = "result_code"

        @JvmStatic
        private val XML_NAME_KEY: String = "key"

        private val SIGNATURE_REPLACEMENT: Pattern = Pattern.compile("(?<=<sign>)(.*?)(?=</sign>)")

    }

    @Suppress("SpellCheckingInspection")
    private val xstream: XStream = XStream()

    init {
        XStream.setupDefaultSecurity(xstream)
        xstream.allowTypes(arrayOf(TreeMap::class.java))
        xstream.alias(XML_NODE_ROOT, TreeMap::class.java)
        xstream.registerConverter(NestedMapConverter(OrdinalType.ASCII))
    }



    fun sign(content: String, request: Request): String {
        @Suppress("UNCHECKED_CAST")
        val elements = xstream.fromXML(content) as TreeMap<String, String>
        elements.remove(XML_NODE_SIGN)

        val pairs = StringUtils.pair(elements)
        val data = pairs + AMPERSAND + XML_NAME_KEY + EQUAL_SIGN + request.config.secretKey
        val sign = Hex.encodeHexString(SecurityUtils.digest(request.config.signatureAlgorithm!!,
                data.toByteArray(Charset.forName(request.config.encoding))))
                .toUpperCase()
        return SIGNATURE_REPLACEMENT.matcher(content).replaceFirst(sign)
    }

    fun verify(content: String, request: Request) {
        @Suppress("UNCHECKED_CAST")
        val elements = xstream.fromXML(content) as MutableMap<String, String>

        if (CODE_SUCCESS != elements[XML_NODE_CODE]) {
            return
        }

        val originalSign = elements.remove(XML_NODE_SIGN)
        val pairs = StringUtils.pair(elements)
        val data = pairs + AMPERSAND + XML_NAME_KEY + EQUAL_SIGN + request.config.secretKey
        val sign = Hex.encodeHexString(SecurityUtils.digest(request.config.signatureAlgorithm!!,
                data.toByteArray(Charset.forName(request.config.encoding))))
                .toUpperCase()
        if (!sign.equals(originalSign, true)) {
            log.error("The invalid signature: '$originalSign' was found in response '$data'")
            throw ApiException(msg = "Invalid signature was found in response")
        }
    }


}
