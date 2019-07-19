package it.barusu.chris.channel.process

data class Config(
        var channelNo: String? = null,
        var channelType: String? = null,
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