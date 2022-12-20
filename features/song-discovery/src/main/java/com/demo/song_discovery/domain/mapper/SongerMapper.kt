package com.demo.song_discovery.domain.mapper

import com.demo.song_discovery.data.model.SongDTO
import com.demo.song_discovery.domain.model.Song

fun List<SongDTO>.toSongs() = map{ it.toSong()}

fun SongDTO.toSong() = Song(releaseDate, artWorkUrl, track, artist, shortDescription)