package com.bpavuk.posterapp.ui.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpavuk.posterapp.ui.navigation.PosterNavigation

@Composable
fun BottomAppBar(
    onClick: (PosterNavigation) -> Unit = {},
    activeButton: PosterNavigation
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 16.dp
    ) {
        for (i in PosterNavigation.values()) {
            IconButton(
                onClick = { onClick(i) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                interactionSource = NoRippleInteractionSource()
            ) {
                AppBarIcon(
                    icon = i,
                    isActive = activeButton == i,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 32.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun AppBarIcon(
    icon: PosterNavigation,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val buttonWidth by animateDpAsState(
        targetValue = if (isActive) 60.dp else 24.dp,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .width(buttonWidth)
                .align(Alignment.CenterHorizontally)
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .background(
                    color = if (isActive) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceColorAtElevation(16.dp)
                    }
                )
        ) {
            Icon(
                imageVector = icon.icon,
                contentDescription = stringResource(id = icon.string),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = stringResource(id = icon.string),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar(activeButton = PosterNavigation.HOME)
}