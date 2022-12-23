package com.demo.song_discovery.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.song_discovery.R
import com.demo.song_discovery.databinding.ActivitySearchSongBinding
import com.demo.song_discovery.domain.model.Song
import com.demo.song_discovery.view.adapter.SongListAdapter
import com.demo.song_discovery.view.core.hideKeyboard
import com.demo.song_discovery.view.core.hideShrink
import com.demo.song_discovery.view.core.show
import com.demo.song_discovery.view.state.SongViewState
import com.demo.song_discovery.view.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onResume() {
        super.onResume()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    hideKeyboard()
                    viewModel.querySong(it)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initView() {
        songListAdapter = SongListAdapter()
        with(binding.songList) {
            layoutManager = GridLayoutManager(this@SearchSongActivity, 2)
            adapter = songListAdapter
        }
    }

    private fun initObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when (it) {
                    SongViewState.Failure -> setError()
                    SongViewState.Idle -> setIdle()
                    SongViewState.Searching -> setLoading()
                    is SongViewState.Success -> showSongList(it.songs)
                }
            }
        }
    }

    private fun setError() {
        Snackbar.make(findViewById(R.id.main_content), R.string.error_message, Snackbar.LENGTH_SHORT).show()
        binding.loading.hideShrink()
        binding.songList.hideShrink()
    }

    private fun setLoading() {
        binding.loading.show()
    }

    private fun setIdle() {
        binding.loading.hideShrink()
        binding.songList.hideShrink()
    }

    private fun showSongList(data: List<Song>) {
        binding.loading.hideShrink()
        binding.songList.show()
        songListAdapter.submitList(data)
        if(data.isEmpty()){
            Snackbar.make(findViewById(R.id.main_content), R.string.empty_state, Snackbar.LENGTH_SHORT).show()
        }
    }

}