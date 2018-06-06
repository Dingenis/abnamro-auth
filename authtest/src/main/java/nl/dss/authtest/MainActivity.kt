package nl.dss.authtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import nl.dss.abnamro.auth.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the authentication library
        val config : AuthenticationConfig = AuthenticationConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Authentication.setup(config)

        // Start the authentication process
        Authentication.authenticate(object : Callback<AccessToken> {
            override fun onSuccess(value: AccessToken) {
                status_text.text = value.token
            }

            override fun onApiError(errors: List<ApiError>) {
                val builder : StringBuilder = StringBuilder()
                for(error : ApiError in errors) {
                    builder.appendln(error.message)
                }
                status_text.text = builder.toString()
            }

            override fun onException(throwable: Throwable) {
                throw throwable
            }
        })
    }
}
