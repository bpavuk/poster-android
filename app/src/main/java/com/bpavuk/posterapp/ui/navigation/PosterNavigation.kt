package com.bpavuk.posterapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.bpavuk.posterapp.R

enum class PosterNavigation(@StringRes val string: Int, val icon: ImageVector) {
    HOME(R.string.home, icon = Icons.Outlined.Home),
    ME(R.string.me, icon = Icons.Outlined.AccountBox)
}