package com.demo.song_discovery.data.model
import kotlinx.serialization.Serializable

@Serializable
data class ItunesResponse(val resultCount : Int, val results : List<SongDTO>)
