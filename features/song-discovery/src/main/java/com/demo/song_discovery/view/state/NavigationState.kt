package com.demo.song_discovery.view.state

import com.demo.song_discovery.domain.model.Song

sealed class NavigationViewState(){
    object SearchSongView : NavigationViewState()
    data class SongDetailsView(val song : Song) : NavigationViewState()
}