package com.raerossi.retotecnico.data

sealed class LoginResult {
    object Error : LoginResult()
    object Success : LoginResult()
}