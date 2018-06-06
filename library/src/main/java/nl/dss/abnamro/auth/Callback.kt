package nl.dss.abnamro.auth

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
interface Callback<T> {
    fun onSuccess(value : T)

    fun onApiError(errors : List<ApiError>)

    fun onException(throwable : Throwable)
}