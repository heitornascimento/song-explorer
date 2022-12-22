package com.demo.song_discovery.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SongDTO(
    val releaseDate: String,
    @SerialName("artworkUrl100") val artWorkUrl: String,
    @SerialName("trackName") val track: String? = "",
    @SerialName("artistName") val artist: String,
    val shortDescription: String? = "",
    val longDescription: String? = "",
    val primaryGenreName: String? = ""
)