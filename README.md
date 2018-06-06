# abnamro-auth
Android authentication library for ABN-AMRO APIs

## Getting Started
1. Add the library to your project

```gradle
implementation 'nl.dss.abnamro:auth:0.0.1'
```

2. Initialize the library with your API key and your private key
```kotlin
        // Setup the authentication library
        val config = AuthenticationConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Authentication.setup(config)
```

3. All done! You can now use the library to authenticate for the ABN AMRO APIs
```kotlin
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
```
