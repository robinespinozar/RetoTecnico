package com.raerossi.retotecnico.data.remote.auth

import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import javax.inject.Inject

//Esta clase simulará la autenticación de un usuario (Firebase Auth, Api Rest, etc ..)
class AuthService @Inject constructor() {

    suspend fun login(user: String, password: String): LoginResult {
        val validUser = "Admin"
        val validPassword = "Password.123"

        return if (user == validUser && password == validPassword) LoginResult.Success else LoginResult.Error
    }
}