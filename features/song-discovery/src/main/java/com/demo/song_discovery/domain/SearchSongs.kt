package com.demo.song_discovery.domain

import com.demo.song_discovery.di.ItunesRepo
import com.demo.song_discovery.domain.mapper.toSongs
import com.demo.song_discovery.domain.model.Song
import javax.inject.Inject

class SearchSongs @Inject constructor(@ItunesRepo private val repository: ItunesRepository) {

    suspend operator fun invoke(query: String): List<Song> = repository.fetchResult(query).toSongs()
}