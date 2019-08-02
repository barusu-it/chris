package it.barusu.chris.channel.config

import it.barusu.chris.common.ChannelType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cs_channel_secret_config")
data class SecretConfig(
        @Id
        var channelNo: String? = null,
        var channelType: ChannelType? = null,
        var baseUrl: String? = null,
        var callbackUrl: String? = null,
        var returnUrl: String? = null,
        var encoding: String? = null,
        var merchantNo: String? = null,
        var username: String? = null,
        var password: String? = null,
        var privateKeyType: String? = null,
        var privateKey: String? = null,
        var privateKeyPassword: String? = null,
        var publicKeyType: String? = null,
        var publicKey: String? = null,
        var signatureAlgorithm: String? = null,
        var secretKeyType: String? = null,
        var secretKey: String? = null,
        var appId: String? = null)