package com.raerossi.retotecnico.ui.features.login

data class LoginCallbacks(
    val onLoginClick: () -> Unit,
    val onLoginChanged: (String, String) -> Unit,
    val onErrorDialogClick: () -> Unit
)