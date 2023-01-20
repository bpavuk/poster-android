package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User


object Datasource {
    val users = listOf(
        User(
            id = 0,
            userName = "beckie228",
            profileImgUrl = "sample img url"
        ),
        User(
            id = 1,
            userName = "enchanted_ant",
            profileImgUrl = "sample img url"
        ),
        User(
            id = 2,
            userName = "favey_inSauce",
            profileImgUrl = "sample img url"
        ),
    )

    val fakePosts = listOf(
        Post(
            id = 0,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}",
            author = users[2]
        ),
        Post(
            id = 1,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/mascaloona/${(400..1920).random()}/${(400..1920).random()}",
            author = users[0]
        ),
        Post(
            id = 2,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/doggy/${(400..1920).random()}/${(400..1920).random()}",
            author = users[1]
        ),
        Post(
            id = 3,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}",
            author = users[1]
        )
    )
}