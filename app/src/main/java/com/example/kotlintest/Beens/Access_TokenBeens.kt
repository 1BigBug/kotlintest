package com.example.kotlintest.Beens

class Access_TokenBeens {

    private var access_token: String? = null
    private var token_type: String? = null
    private var refresh_token: String? = null
    private var expires_in: Int = 0
    private var scope: String? = null
    private var jti: String? = null

    fun getAccess_token(): String? {
        return access_token
    }

    fun setAccess_token(access_token: String) {
        this.access_token = access_token
    }

    fun getRefresh_token(): String? {
        return refresh_token
    }

    fun setRefresh_token(refresh_token: String) {
        this.refresh_token = refresh_token
    }

    fun getToken_type(): String? {
        return token_type
    }

    fun setToken_type(token_type: String) {
        this.token_type = token_type
    }

    fun getExpires_in(): Int {
        return expires_in
    }

    fun setExpires_in(expires_in: Int) {
        this.expires_in = expires_in
    }

    fun getScope(): String? {
        return scope
    }

    fun setScope(scope: String) {
        this.scope = scope
    }

    fun getJti(): String? {
        return jti
    }

    fun setJti(jti: String) {
        this.jti = jti
    }

}