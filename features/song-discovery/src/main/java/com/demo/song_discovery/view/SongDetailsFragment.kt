package com.demo.song_discovery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.demo.song_discovery.R
import com.demo.song_discovery.databinding.FragmentSongDetailsBinding
import com.demo.song_discovery.view.state.NavigationViewState
import com.demo.song_discovery.view.viewmodel.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailsFragment : Fragment() {

    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private lateinit var binding: FragmentSongDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val song =
            (navigationViewModel.navigationState.value as NavigationViewState.SongDetailsView).song

        with(binding) {
            cover.load(song.artWorkUrl)
            artistName.text = song.artist
            trackName.text = song.track
            releaseDate.text = song.releaseDate
            shortDescription.text = song.shortDescription
        }

    }
}