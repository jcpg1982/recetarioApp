package pe.com.master.machines.constants.security

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object EncryptionHelper {

    private const val ALGORITHM = "AES/GCM/NoPadding"
    private const val IV_SIZE = 12 // Tamaño del IV en bytes
    private const val TAG_LENGTH_BIT = 128 // Tamaño del TAG en bits

    fun encrypt(data: String, key: SecretKey): String {
        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val dataBytes = data.toByteArray(Charsets.UTF_8)
            val encryptedBytes = cipher.doFinal(dataBytes)
            val iv = cipher.iv
            return Base64.encodeToString(iv + encryptedBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            throw e
        }
    }

    fun decrypt(encryptedData: String, key: SecretKey): String {
        val data = Base64.decode(encryptedData, Base64.DEFAULT)
        val iv = data.sliceArray(0 until IV_SIZE)
        val encryptedBytes = data.sliceArray(IV_SIZE until data.size)
        val cipher = Cipher.getInstance(ALGORITHM)
        val gcmParameterSpec = GCMParameterSpec(TAG_LENGTH_BIT, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }
}