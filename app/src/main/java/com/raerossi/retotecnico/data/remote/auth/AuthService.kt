package com.raerossi.retotecnico.data.remote.auth

import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import com.raerossi.retotecnico.utils.Constants
import javax.inject.Inject

//Esta clase simulará la autenticación de un usuario (Firebase Auth, Api Rest, etc ..)
class AuthService @Inject constructor() {

    suspend fun login(user: String, password: String): LoginResult {
        val validUser = Constants.USER
        val validPassword = Constants.PASSWORD

        return if (user == validUser && password == validPassword) LoginResult.Success else LoginResult.Error
    }
}