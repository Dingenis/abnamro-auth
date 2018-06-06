package nl.dss.abnamro.auth

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 5-6-2018
 * Description...
 */
data class AuthenticationConfig(val apiKey : String, val key : String, val refresh : Boolean = true) {

    var client : OkHttpClient.Builder = OkHttpClient.Builder()

    var retrofit : Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://api-sandbox.abnamro.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
}