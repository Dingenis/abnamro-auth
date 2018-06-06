package nl.dss.abnamro.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 6-6-2018
 * Description...
 */
enum class ErrorCode {

    /**
     * Defines ERR_1001_001
     *
     * Malformed or incorrect Authorization header
     *
     */
    @SerializedName("ERR_1001_001")
    INCORRECT_HEADER,

    /**
     * Defines ERR_1002_001
     *
     * Basic authentication header missing
     *
     */
    @SerializedName("ERR_1002_001")
    MISSING_HEADER_BASIC,

    /**
     * Defines ERR_1002_002
     *
     * Basic authentication header missing
     *
     */
    @SerializedName("ERR_1002_002")
    MISSING_HEADER_TOKEN,

    /**
     * Defines ERR_1002_003
     *
     * Basic authentication header missing
     *
     */
    @SerializedName("ERR_1002_003")
    MISSING_HEADER_API_KEY,




}