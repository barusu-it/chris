package it.barusu.chris.channel

import it.barusu.chris.channel.config.ChannelSecret
import it.barusu.chris.common.ChannelType
import it.barusu.chris.util.StringUtils
import it.barusu.chris.util.StringUtils.Companion.DASH
import it.barusu.chris.util.json.JsonProviderHolder
import org.apache.commons.io.IOUtils
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import java.nio.charset.Charset
import java.security.SecureRandom

abstract class AbstractChannelTests {

    companion object {

        const val DEFAULT_CHANNEL_NO: String = "DEFAULT"
        private const val DEFAULT_ENVIRONMENT: String = "dev"
        private const val BASE_DIRECTION: String = "channel-secret"
        private const val SUFFIX = ".json"

        @JvmStatic
        private val SEPARATOR = System.getProperty("file.separator")

        @JvmStatic
        private val ENVIRONMENT = System.getProperty("env", DEFAULT_ENVIRONMENT)
    }

    protected fun getSecret(type: ChannelType, channelNo: String): ChannelSecret {
        val fileName = BASE_DIRECTION + SEPARATOR + ENVIRONMENT + SEPARATOR + type.name + DASH + channelNo + SUFFIX
        val content = IOUtils.toString(Thread.currentThread().contextClassLoader.getResourceAsStream(fileName),
                Charset.forName(StringUtils.UTF_8))
        return JsonProviderHolder.JACKSON.parse(content, ChannelSecret::class.java)
    }

    protected fun httpClient(): HttpClient {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(30000)
                        .setConnectionRequestTimeout(30000)
                        .setSocketTimeout(30000)
                        .build())
                .setSSLContext(SSLContextBuilder.create()
                        .setProtocol("TLS")
                        .loadTrustMaterial(null, TrustSelfSignedStrategy())
                        .setSecureRandom(SecureRandom())
                        .build())
                .setSSLHostnameVerifier(NoopHostnameVerifier())
                .build()
    }

}