package nl.dss.abnamro.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
data class AccessToken(@SerializedName("access_token")
                      val token : String,

                       @SerializedName("expires_in")
                      val expireDate : Long,

                       @SerializedName("scope")
                      val scope : String,

                       @SerializedName("token_type")
                      val type : TokenType)