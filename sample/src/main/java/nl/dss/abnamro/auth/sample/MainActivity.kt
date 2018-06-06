package nl.dss.abnamro.auth.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import nl.dss.abnamro.auth.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Setup the authentication library
        val config = AuthenticationConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Authentication.setup(config)

        // Start the authentication process
        Authentication.authenticate(object : Callback<AccessToken> {
            override fun onSuccess(value: AccessToken) {
                status_text.text = value.token
            }

            override fun onApiError(errors: List<ApiError>) {
                val builder = StringBuilder()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
