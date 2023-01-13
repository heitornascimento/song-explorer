package com.demo.song_discovery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.song_discovery.R
import com.demo.song_discovery.databinding.FragmentSearchSongBinding
import com.demo.song_discovery.domain.model.Song
import com.demo.song_discovery.view.adapter.SongListAdapter
import com.demo.song_discovery.view.core.hideSearchKeyboard
import com.demo.song_discovery.view.core.hideShrink
import com.demo.song_discovery.view.core.show
import com.demo.song_discovery.view.state.NavigationViewState
import com.demo.song_discovery.view.state.SongViewState
import com.demo.song_discovery.view.viewmodel.NavigationViewModel
import com.demo.song_discovery.view.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSongFragment : Fragment() {

    private lateinit var binding: FragmentSearchSongBinding
    private lateinit var songListAdapter: SongListAdapter
    private val viewModel: SearchViewModel by viewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchSongBinding.inflate(inflater, container, false)
        initView()
        initObserver()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    hideSearchKeyboard()
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
        songListAdapter = SongListAdapter(this::onSongDetails)
        with(binding.songList) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = songListAdapter
        }
    }

    private fun initObserver() {
        lifecycleScope.launchWhenResumed {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
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
    }

    private fun setError() {
        Snackbar.make(
            binding.root.findViewById(R.id.main_content),
            R.string.error_message,
            Snackbar.LENGTH_SHORT
        ).show()
        binding.loading.hideShrink()
        binding.songList.hideShrink()
    }

    private fun setLoading() = binding.loading.show()

    private fun setIdle() {
        binding.loading.hideShrink()
        binding.songList.hideShrink()
    }

    private fun showSongList(data: List<Song>) {
        binding.loading.hideShrink()
        binding.songList.show()
        songListAdapter.submitList(data)
        if (data.isEmpty()) {
            Snackbar.make(
                binding.root.findViewById(R.id.main_content),
                R.string.empty_state,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun onSongDetails(song: Song) =
        navigationViewModel.navigationState.tryEmit(NavigationViewState.SongDetailsView(song))
}