package com.bpavuk.posterapp.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpavuk.posterapp.R
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.ui.AppViewModelProvider
import com.bpavuk.posterapp.ui.common.viewmodels.AccountScreenViewModel
import com.bpavuk.posterapp.ui.common.viewmodels.UiState
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

@Composable
fun PostsGrid(
    postsList: List<Post>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(96.dp),
        modifier = modifier
    ) {
        items(postsList) {post ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.imgURL)
                    .build(),
                contentDescription = post.author.userName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            )
        }
    }
}

@Composable
fun AccountScreen(
    viewModel: AccountScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    when (val uiState = viewModel.uiState) {
        is UiState.Success -> {
            Column {
                AccountCard(user = uiState.user)
                PostsGrid(postsList = uiState.postsList ?: emptyList())
            }
        }
        is UiState.Error -> {
            when (uiState.code) {
                401 -> LoginScreen()
                else -> Text(text = stringResource(id = R.string.internet_oops))
            }
        }
        UiState.Loading -> {
            Text(text = stringResource(id = R.string.loading))
        }
    }
}
