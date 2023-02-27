package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpavuk.posterapp.ui.AppViewModelProvider
import com.bpavuk.posterapp.ui.common.viewmodels.PosterAppViewModel
import com.bpavuk.posterapp.ui.navigation.PosterNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterApp(
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    viewModel: PosterAppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState

    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        onClick = { viewModel.changeScreen(it) },
                        activeButton = uiState.currentScreen
                    )
                },
                modifier = modifier
            ) {
                when (uiState.currentScreen) {
                    PosterNavigation.HOME -> {
                        PostCardsListScreen(
                            windowWidthSizeClass = windowWidthSizeClass,
                            modifier = Modifier.padding(it)
                        )
                    }
                    PosterNavigation.ME -> {
                        LoginScreen()
                    }
                }
            }
        }
        WindowWidthSizeClass.Medium -> {
            TODO()
        }
        WindowWidthSizeClass.Expanded -> {
            TODO()
        }
    }
}
