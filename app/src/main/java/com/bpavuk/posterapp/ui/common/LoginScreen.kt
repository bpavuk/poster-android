package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpavuk.posterapp.R
import com.bpavuk.posterapp.ui.AppViewModelProvider
import com.bpavuk.posterapp.ui.theme.PosterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    onLogin: () -> Unit,
    onUsernameInput: (String) -> Unit,
    uiState: LoginScreenUiState
) {
    OutlinedTextField(
        value = uiState.username,
        onValueChange = onUsernameInput,
        label = { Text(text = stringResource(id = R.string.username)) },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onLogin() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
    Spacer(modifier = Modifier.size(16.dp))
    OutlinedTextField(
        value = uiState.password,
        onValueChange = onPasswordInput,
        label = { Text(text = stringResource(id = R.string.password)) },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onLogin() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
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
fun LoginScreen(modifier: Modifier = Modifier) {
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
                onLogin = { viewModel.login() }
            )
            viewModel.uiState.token?.let { Text(text = it) }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    PosterTheme {
        Surface(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
        ) {
            LoginScreen()
        }
    }
}