package pe.com.master.machines.constants.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import pe.com.master.machines.constants.BuildConfig.KEYSTORE_ALIAS
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableKeyException
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object KeyStoreHelper {

    fun generateKeyIfNeeded() {
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            if (keyStore.getKey(KEYSTORE_ALIAS, null) == null) {
                generateKey()
            }
        } catch (e: Exception) {
            throw RuntimeException("Error verificando o generando la clave: ${e.message}", e)
        }
    }


    private fun generateKey(): SecretKey {
        try {
            val keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val keyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEYSTORE_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            keyGenerator.init(keyGenParameterSpec)
            return keyGenerator.generateKey()
        } catch (e: Exception) {
            throw RuntimeException("Error generando la clave: ${e.message}", e)
        }
    }

    fun getKey(): SecretKey {
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            return keyStore.getKey(KEYSTORE_ALIAS, null) as SecretKey
        } catch (e: KeyStoreException) {
            throw RuntimeException("Error al acceder al Keystore: ${e.message}", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Algoritmo no soportado: ${e.message}", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Error recuperando la clave: ${e.message}", e)
        }
    }

}