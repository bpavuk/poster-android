package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.ui.theme.PosterTheme

@Composable
fun AccountPhoto(
    username: String,
    url: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = username,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun AccountCard(user: User) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Row {
                AccountPhoto(
                    username = user.userName,
                    url = user.profileImgUrl,
                    modifier = Modifier
                        .width(120.dp)
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.extraLarge)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = user.userName, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview
@Composable
fun AccountPreview() {
    val fakeUser = User(userName = "fuckery", id = 10, profileImgUrl = "https://picsum.photos/200/300")
    PosterTheme {
        AccountCard(user = fakeUser)
    }
}