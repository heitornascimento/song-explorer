package com.demo.song_discovery.domain

import com.demo.song_discovery.data.model.SongDTO


interface ItunesRepository {
    suspend fun fetchResult(query : String) : List<SongDTO>
}