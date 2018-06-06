package nl.dss.abnamro.auth

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Singleton class for accessing authentication commands
 */
object Authentication {

    private var apiKey : String = String()

    private var key : String = String()

    private var client : OkHttpClient = OkHttpClient()

    private var retrofit : Retrofit? = null

    var refresh : Boolean = true

    var token : AccessToken? = null

    fun setup(config : AuthenticationConfig) {
        apiKey = config.apiKey
        key = config.key

        this.client = config.client
                .build()

        this.retrofit = config.retrofit
                .client(client)
                .build()

        refresh = config.refresh
    }

    fun authenticate(callback : nl.dss.abnamro.auth.Callback<AccessToken>) {
        val service = getService()
        val key = KeyFactory.generatePrivateKey(key)
        val sendToken = TokenFactory.generate(apiKey, key)
        service.authenticate(apiKey, sendToken).enqueue(Callback(callback))
    }

    internal data class Callback(private val callback: nl.dss.abnamro.auth.Callback<AccessToken>?) : retrofit2.Callback<AccessToken> {
        override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
            if(response.isSuccessful) {
                val token = response.body()
                if(token != null) {
                    callback?.onSuccess(token)
                }
                Authentication.token = token
            } else {
                val errorBody = response.errorBody()
                if(errorBody != null) {
                    val json = errorBody.string()
                    val deferred = async {
                        Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                    }
                    launch {
                        val result = deferred.await()
                        callback?.onApiError(result.errors)
                    }
                }
            }
        }

        override fun onFailure(call: Call<AccessToken>, t: Throwable) {
            callback?.onException(t)
        }
    }

    fun getService() : AuthenticationService {
        return retrofit?.create(AuthenticationService::class.java) ?: throw NullPointerException()
    }

}