package com.raerossi.retotecnico.ui.features.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val messageError: String = ""
)