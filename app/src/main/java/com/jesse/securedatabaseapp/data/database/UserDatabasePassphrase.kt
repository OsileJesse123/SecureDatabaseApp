package com.jesse.securedatabaseapp.data.database

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File
import java.security.SecureRandom

/**
 * This class is responsible for managing the passphrase used to encrypt and decrypt the Room database.
 * **/
class UserDatabasePassphrase(private val context: Context) {

    /**
     * This function retrieves the passphrase from a file (ohunelo_passphrase.bin).
     * If the file exists, it reads the passphrase from the encrypted file and returns it as a ByteArray.
     * If the file doesn't exist, it generates a new passphrase using the generatePassphrase function, writes it to the file, and then returns the generated passphrase.
     *
     *  This code ensures that the passphrase used for encrypting the Room database is securely managed.
     *  It checks if the passphrase file exists, reads the passphrase if it does, or generates a new
     *  one if it doesn't. The passphrase is stored in an encrypted file, adding an extra layer of
     *  security. The use of SecureRandom for passphrase generation contributes to the cryptographic
     *  strength of the generated passphrase.
     * **/

    fun getPassphrase(): ByteArray{

        // An EncryptedFile instance is created using the AndroidX Security library.
        // It uses the AES256_GCM_SPEC to create or retrieve a master key for encryption.
        // The encryption scheme is set to AES256_GCM_HKDF_4KB.
        // The file is specified as user_passphrase.bin.
        val file = File(context.filesDir, "user_passphrase.bin")
        val encryptedFile = EncryptedFile.Builder(
            file,
            context,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        return if(file.exists()){
            encryptedFile.openFileInput().use { it.readBytes() }
        } else {
            generatePassphrase().also {
                    passPhrase ->
                encryptedFile.openFileOutput().use { it.write(passPhrase) }
            }
        }
    }

    /**
     * This private function generates a random 32-byte passphrase using a SecureRandom instance.
     * It ensures that the generated passphrase doesn't contain any null bytes (0) to avoid potential issues.
     * **/
    private fun generatePassphrase(): ByteArray{
        // The SecureRandom instance is used to generate a cryptographically secure random passphrase.
        val random = SecureRandom.getInstanceStrong()
        val result = ByteArray(32)

        random.nextBytes(result)
        while (result.contains(0)){
            random.nextBytes(result)
        }

        return result
    }
}