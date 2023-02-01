package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpavuk.posterapp.R
import com.bpavuk.posterapp.ui.AppViewModelProvider
import com.bpavuk.posterapp.ui.theme.PosterTheme

@Composable
fun LoginForm(onLogin: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onLogin) {
        Text(text = stringResource(id = R.string.login))
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val viewModel: LoginScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginForm(onLogin = { viewModel.login() })
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