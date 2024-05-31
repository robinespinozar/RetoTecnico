package com.raerossi.retotecnico.ui.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.retotecnico.data.remote.auth.response.LoginResult
import com.raerossi.retotecnico.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onLoginChanged(user: String, password: String) {
        _user.value = user
        _password.value = password
        _uiState.update { it.copy(isLoginEnabled = enableLogin(user, password)) }
    }

    fun onLoginSelected(user: String, password: String, toMoviesListScreen: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            login(user, password) { toMoviesListScreen() }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun login(user: String, password: String, toMoviesListScreen: () -> Unit) {
        val loginResult = loginUseCase(user, password)
        when (loginResult) {
            LoginResult.Error -> {
                _uiState.update {
                    it.copy(
                        messageError = "Check the information or try again.",
                        showErrorDialog = true
                    )
                }
                //_isLoading.value = false
            }

            LoginResult.Success -> {
                toMoviesListScreen()
            }
        }
    }

    fun hideErrorDialog() {
        _uiState.update { it.copy(showErrorDialog = false) }
    }

    private fun enableLogin(user: String, password: String) =
        validateUser(user) && validatePassword(password)

    private fun validateUser(user: String) = user.length > 4

    private fun validatePassword(password: String) = password.length > 6

}