package com.demo.song_discovery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.demo.network.buildOkHttpClient
import com.demo.network.buildRetrofit
import com.demo.song_discovery.data.api.ItunesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

class SearchSongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_song)

        lifecycleScope.launchWhenResumed {

            val client = buildOkHttpClient()
            val api  =  buildRetrofit(client).create(ItunesAPI::class.java)

            val params = withContext(Dispatchers.IO) {
                URLEncoder.encode("Jack Johnson", "utf-8")
            }
            Log.d("itunes", "\n\n\n URL= ${params}")
            val list = api.fetchQuery(params).results

            list.forEach {
                Log.d("itunes", it.toString())
            }

        }
    }
}