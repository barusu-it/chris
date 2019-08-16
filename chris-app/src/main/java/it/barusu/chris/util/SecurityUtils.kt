package it.barusu.chris.util

import com.google.common.base.Strings
import org.apache.commons.codec.binary.Base64
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.ByteArrayInputStream
import java.security.KeyStore
import java.security.MessageDigest
import java.security.Security

class SecurityUtils {
    companion object {
        const val DEFAULT_PROVIDER: String = BouncyCastleProvider.PROVIDER_NAME
        const val MD5: String = "MD5"
        const val SHA1: String = "MD5"
        const val SHA256: String = "SHA-256"
        const val SHA512: String = "SHA-512"
        const val RSA: String = "RSA"
        const val DSA: String = "DSA"
        const val AES: String = "AES"
        const val DES: String = "DES"
        const val SM2: String = "SM2"
        const val SM3: String = "SM3"

        init {
            Security.addProvider(BouncyCastleProvider())
        }

        @JvmStatic
        fun digest(algorithm: String, data: ByteArray): ByteArray {
            return digest(algorithm, data, null)
        }

        @JvmStatic
        fun digest(algorithm: String, data: ByteArray, salt: ByteArray?): ByteArray {
            val digest = MessageDigest.getInstance(algorithm, DEFAULT_PROVIDER)
            if (salt != null) {
                digest.update(salt)
            }
            digest.update(data)
            return digest.digest()
        }

        @JvmStatic
        fun getKeyStore(type: String, key: String, pwd: String, provider: String?): KeyStore {
            val chars = pwd.toCharArray()
            val keyStore =
                    if (Strings.isNullOrEmpty(provider)) KeyStore.getInstance(type)
                    else KeyStore.getInstance(type, provider)
            keyStore.load(ByteArrayInputStream(Base64.decodeBase64(key)), chars)
            return keyStore
        }

    }
}