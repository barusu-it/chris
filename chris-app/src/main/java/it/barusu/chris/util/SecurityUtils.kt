package it.barusu.chris.util

import org.bouncycastle.jce.provider.BouncyCastleProvider
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

    }
}