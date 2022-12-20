package com.demo.song_discovery.domain.model

import kotlinx.serialization.SerialName

data class Song(
    val releaseDate: String,
    val artWorkUrl: String,
    val track: String,
    val artist: String,
    val shortDescription: String?
)