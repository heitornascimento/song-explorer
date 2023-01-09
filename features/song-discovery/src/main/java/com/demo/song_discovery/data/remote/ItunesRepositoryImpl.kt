package com.demo.song_discovery.data.remote

import com.demo.song_discovery.domain.ItunesRepository
import com.demo.song_discovery.data.api.ItunesAPI
import com.demo.song_discovery.data.model.SongDTO
import com.demo.song_discovery.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import javax.inject.Inject


class ItunesRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val api: ItunesAPI
) : ItunesRepository {

    override suspend fun fetchResult(query: String): List<SongDTO> = withContext(ioDispatcher) {
        api.fetchQuery(URLEncoder.encode(query, "utf-8")).results
    }

}