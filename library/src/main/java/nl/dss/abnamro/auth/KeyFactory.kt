package nl.dss.abnamro.auth

import android.util.Base64
import java.io.BufferedReader
import java.io.StringReader
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
internal object KeyFactory {

    fun generatePrivateKey(key : String) : PrivateKey {
        val reader = BufferedReader(StringReader(key))

        // Remove the "BEGIN" and "END" lines, as well as any whitespace
        var pem = reader.use(BufferedReader::readText)
        pem = pem.replace("-----BEGIN RSA PRIVATE KEY-----", "")
        pem =  pem.replace("-----END RSA PRIVATE KEY-----", "")
        pem = pem.replace("\\s+".toRegex(), "")

        // Base64 decode the result
        val pkcs8EncodedBytes = Base64.decode(pem, Base64.DEFAULT)

        // Extract the private key
        val keySpec = PKCS8EncodedKeySpec(pkcs8EncodedBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpec) as PrivateKey
    }
}