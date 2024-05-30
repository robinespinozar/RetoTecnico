package com.raerossi.retotecnico.data

import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import com.raerossi.retotecnico.data.remote.auth.AuthService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiAuth: AuthService
){
    suspend fun login(user: String, password: String): LoginResult {
        return apiAuth.login(user, password)
    }
}