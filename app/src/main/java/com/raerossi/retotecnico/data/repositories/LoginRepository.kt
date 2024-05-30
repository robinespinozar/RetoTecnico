package com.raerossi.retotecnico.data.repositories

import com.raerossi.retotecnico.data.LoginResult
import com.raerossi.retotecnico.data.remote.AuthService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiAuth: AuthService
){
    suspend fun login(user: String, password: String): LoginResult {
        return apiAuth.login(user, password)
    }
}