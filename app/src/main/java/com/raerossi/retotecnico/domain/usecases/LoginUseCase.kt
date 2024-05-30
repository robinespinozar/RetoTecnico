package com.raerossi.retotecnico.domain.usecases

import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import com.raerossi.retotecnico.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: String, password: String): LoginResult {
        return repository.login(user,password)
    }
}