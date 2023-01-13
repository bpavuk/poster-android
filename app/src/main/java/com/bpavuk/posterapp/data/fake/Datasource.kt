package com.bpavuk.posterapp.data.fake

import com.bpavuk.posterapp.model.Post


object Datasource {
    val fakePosts = listOf(
        Post(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/mascaloona/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/doggy/${(400..1920).random()}/${(400..1920).random()}"
        ),
        Post(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imgURL = "https://picsum.photos/seed/kittens/${(400..1920).random()}/${(400..1920).random()}"
        )
    )
}