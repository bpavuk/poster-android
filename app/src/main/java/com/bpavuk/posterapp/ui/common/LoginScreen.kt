package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpavuk.posterapp.R
import com.bpavuk.posterapp.ui.AppViewModelProvider
import com.bpavuk.posterapp.ui.common.viewmodels.LoginScreenUiState
import com.bpavuk.posterapp.ui.common.viewmodels.LoginScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    onLogin: () -> Unit,
    onUsernameInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    uiState: LoginScreenUiState
) {
    val username = uiState.username
    val password = uiState.password
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = username,
        onValueChange = onUsernameInput,
        label = { Text(text = stringResource(id = R.string.username)) },
        singleLine = true,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Ascii
        ),
        isError = uiState.error != null
    )
    Spacer(modifier = Modifier.size(16.dp))
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordInput,
        label = { Text(text = stringResource(id = R.string.password)) },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onLogin() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        isError = uiState.error != null
    )
    Spacer(modifier = Modifier.size(16.dp))
    Button(
        onClick = onLogin,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSuccessfulLogin: () -> Unit
) {
    val viewModel: LoginScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 54.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LoginForm(
                uiState = viewModel.uiState,
                onUsernameInput = { viewModel.inputUsername(it) },
                onPasswordInput = { viewModel.inputPassword(it) },
                onLogin = { viewModel.login() }
            )
        }
        if (viewModel.uiState.loggedInUser != null) onSuccessfulLogin()
    }
}
