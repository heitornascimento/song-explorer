package com.demo.song_discovery.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.song_discovery.databinding.ActivitySearchSongBinding
import com.demo.song_discovery.domain.model.Song
import com.demo.song_discovery.view.adapter.SongListAdapter
import com.demo.song_discovery.view.state.SongViewState
import com.demo.song_discovery.view.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder

@AndroidEntryPoint
class SearchSongActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSongBinding
    private lateinit var songListAdapter: SongListAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObserver()
    }

    private fun initView() {
        songListAdapter = SongListAdapter()
        with(binding.songList) {
            layoutManager = LinearLayoutManager(this@SearchSongActivity)
            adapter = songListAdapter
        }
    }

    private fun initObserver() {
        lifecycleScope.launchWhenResumed {

            val params = withContext(Dispatchers.IO) {
                URLEncoder.encode("Jack Johnson", "utf-8")
            }
            viewModel.querySong(params)
            viewModel.state.collect {
                when (it) {
                    SongViewState.Failure -> {}
                    SongViewState.Idle -> viewModel.querySong(params)
                    SongViewState.Searching -> {}
                    is SongViewState.Success -> showSongList(it.songs)
                }
            }

        }
    }

    private fun showSongList(data: List<Song>) = songListAdapter.submitList(data)
}