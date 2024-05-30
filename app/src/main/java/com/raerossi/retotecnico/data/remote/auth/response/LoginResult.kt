package com.raerossi.retotecnico.data.remote.auth.response

sealed class LoginResult {
    object Error : LoginResult()
    object Success : LoginResult()
}