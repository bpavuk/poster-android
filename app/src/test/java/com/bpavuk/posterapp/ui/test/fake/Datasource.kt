package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.model.Post


object Datasource {
    val fakePosts = listOf(
        Post(
            id = 0,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            id = 1,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/mascaloona/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            id = 2,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/doggy/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            id = 3,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}"
        )
    )
}