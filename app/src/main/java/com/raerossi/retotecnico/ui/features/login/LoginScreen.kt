package com.raerossi.retotecnico.ui.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.retotecnico.R
import com.raerossi.retotecnico.ui.components.ErrorDialog
import com.raerossi.retotecnico.ui.components.GradientButton
import com.raerossi.retotecnico.ui.components.LoadingScreen
import com.raerossi.retotecnico.ui.components.PasswordInputField
import com.raerossi.retotecnico.ui.components.SetSystemColors
import com.raerossi.retotecnico.ui.components.UserInputField
import com.raerossi.retotecnico.ui.components.VerticalSpacer
import com.raerossi.retotecnico.ui.theme.primaryGradient

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {
    SetSystemColors(colorStatusBar = Color(0x2673D3B0))

    val user by viewModel.user.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val uiState by viewModel.uiState.collectAsState(LoginUiState())

    LoginScreen(
        user = user,
        password = password,
        uiState = uiState,
        callbacks = LoginCallbacks(
            onLoginClick = { viewModel.onLoginSelected(user,password) { onLoginClick() } },
            onLoginChanged = { user, password -> viewModel.onLoginChanged(user,password) },
            onErrorDialogClick = { viewModel.hideErrorDialog() }
        )
    )
}

@Composable
fun LoginScreen(
    user: String,
    password: String,
    uiState: LoginUiState,
    callbacks: LoginCallbacks
) {
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        if (uiState.showErrorDialog) {
            ErrorDialog(
                message = uiState.messageError,
                onDissmis = { callbacks.onErrorDialogClick() }
            )
        } else {
            LoginContent(
                user = user,
                password = password,
                isLoginEnabled = uiState.isLoginEnabled,
                onLoginChanged = { user, password -> callbacks.onLoginChanged(user, password) },
                onLoginClick = { callbacks.onLoginClick() }
            )
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    user: String,
    password: String,
    isLoginEnabled: Boolean,
    onLoginChanged: (String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        LoginHeader(modifier = Modifier.align(Alignment.TopCenter))
        LoginBody(
            modifier = Modifier.align(Alignment.Center),
            user = user,
            password = password,
            isLoginEnabled = isLoginEnabled,
            onLoginChanged = { user, password -> onLoginChanged(user, password) },
            onLoginSelected = { onLoginClick() }
        )
        LoginFooter(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
    user: String,
    password: String,
    isLoginEnabled: Boolean,
    onLoginChanged: (String, String) -> Unit,
    onLoginSelected: () -> Unit
) {
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {
        ImageAndNameLogo(Modifier.align(Alignment.CenterHorizontally))
        VerticalSpacer(16)
        User(user) { onLoginChanged(it, password) }
        VerticalSpacer(8)
        Password(password) { onLoginChanged(user, it) }
        VerticalSpacer(32)
        LoginButton(isLoginEnabled) { onLoginSelected() }
    }
}

@Composable
fun ImageAndNameLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.padding(start = 16.dp),
        painter = painterResource(id = R.drawable.logo_gradient),
        contentDescription = "logo"
    )
}


@Composable
private fun User(
    user: String,
    onTextChanged: (String) -> Unit
) {
    UserInputField(user = user, onTextChanged = { onTextChanged(it) })
}

@Composable
private fun Password(
    password: String,
    onTextChanged: (String) -> Unit
) {
    PasswordInputField(password = password, onTextChanged = { onTextChanged(it) })
}

@Composable
private fun LoginButton(
    isLoginEnabled: Boolean,
    onLoginClick: () -> Unit
) {
    GradientButton(
        text = "Log In",
        textColor = Color.White,
        enabled = isLoginEnabled,
        gradient = MaterialTheme.colorScheme.primaryGradient,
        onClick = { onLoginClick() }
    )
}

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bg_login_header),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 8.dp)
    ) {
    }
}

@Composable
fun LoginFooter(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bg_login_footer),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 8.dp)
    ) {
    }
}