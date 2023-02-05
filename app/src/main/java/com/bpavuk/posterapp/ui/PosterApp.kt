package com.bpavuk.posterapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bpavuk.posterapp.ui.common.PostCardsListScreen

@Composable
fun PosterApp(
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    PostCardsListScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        modifier = modifier
    )
}
