package nl.dss.abnamro.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
enum class TokenType {
    @SerializedName("Bearer")
    Bearer
}