package com.demo.song_discovery.data.api

import com.demo.song_discovery.data.model.ItunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_PATH = "/search"
interface ItunesAPI {

    @GET(QUERY_PATH)
    suspend fun fetchQuery(@Query("term") query : String) : ItunesResponse
}