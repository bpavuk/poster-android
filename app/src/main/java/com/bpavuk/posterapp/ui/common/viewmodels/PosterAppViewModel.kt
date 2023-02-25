package com.bpavuk.posterapp.ui.common.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bpavuk.posterapp.ui.navigation.PosterNavigation

class PosterAppViewModel: ViewModel() {
    var uiState by mutableStateOf(PosterAppUiState())
        private set

    init { changeScreen(destination = PosterNavigation.HOME) }

    fun changeScreen(destination: PosterNavigation) {
        uiState = uiState.copy(currentScreen = destination)
        Log.d("fuckery", "$destination")
    }
}

data class PosterAppUiState(
    val currentScreen: PosterNavigation = PosterNavigation.HOME
)