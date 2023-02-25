package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpavuk.posterapp.ui.navigation.PosterNavigation

@Composable
fun BottomAppBar(onClick: (PosterNavigation) -> Unit = {}) {
    NavigationBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .wrapContentSize(align = Alignment.Center)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            for (i in PosterNavigation.values()) {
                IconButton(onClick = { onClick(i) }) {
                    AppBarIcon(icon = i)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AppBarIcon(icon: PosterNavigation) {
    Column {
        Icon(
            imageVector = icon.icon,
            contentDescription = stringResource(id = icon.string),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = icon.string),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar()
}