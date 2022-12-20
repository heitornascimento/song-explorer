package com.demo.song_discovery.data.remote

import com.demo.song_discovery.data.ItunesRepository
import com.demo.song_discovery.data.api.ItunesAPI
import com.demo.song_discovery.data.model.SongDTO


class ItunesRepositoryImpl(private val api: ItunesAPI) : ItunesRepository {
    override suspend fun fetchResult(query: String): List<SongDTO> {
        return api.fetchQuery(query).results
    }
}