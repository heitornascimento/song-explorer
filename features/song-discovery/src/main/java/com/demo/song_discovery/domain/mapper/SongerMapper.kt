package com.demo.song_discovery.domain.mapper

import com.demo.song_discovery.core.toFormatDate
import com.demo.song_discovery.data.model.SongDTO
import com.demo.song_discovery.domain.model.Song
import java.io.FileDescriptor
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

fun List<SongDTO>.toSongs() = map { it.toSong() }

fun SongDTO.toSong() =
    Song(
        releaseDate.toFormatDate(),
        convertUrlImageScreenDimension(artWorkUrl),
        track ?: "",
        artist,
        shortDescription
    )


fun convertUrlImageScreenDimension(artWorkUrl: String): String {
    return artWorkUrl.replace("100x100", "800x800")
}