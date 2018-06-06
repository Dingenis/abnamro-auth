package nl.dss.abnamro.auth

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Container for a response with errors
 */
internal data class ErrorResponse(val errors : List<ApiError>)