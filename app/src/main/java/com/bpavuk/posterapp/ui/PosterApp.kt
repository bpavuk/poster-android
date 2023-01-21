package com.bpavuk.posterapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpavuk.posterapp.ui.common.PostCardsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterApp(
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: PosterAppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = viewModel.uiState

    Scaffold { paddingValues ->
        PostCardsList(
            postsList = uiState.postsList,
            windowWidthSizeClass = windowWidthSizeClass,
            onReachedEnd = { viewModel.updatePosts(it) },
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
        )
    }
}
