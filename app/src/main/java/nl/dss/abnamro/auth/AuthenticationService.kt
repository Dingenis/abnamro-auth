package nl.dss.abnamro.auth

import retrofit2.Call
import retrofit2.http.*


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
interface AuthenticationService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("oauth/token")
    fun authenticate(@Header("API-Key") key: String,
                     @Field("client_assertion") token: String,
                     @Field("client_assertion_type") type: String = "urn:ietf:params:oauth:client-assertion-type:jwt-bearer",
                     @Field("grant_type") grant_type: String = "client_credentials",
                     @Field("scope") scope: String = "tikkie"): Call<AccessToken>
}