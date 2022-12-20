package com.demo.song_discovery.view.state

import com.demo.song_discovery.domain.model.Song

sealed class SongViewState(){
    object Idle: SongViewState()
    object Searching: SongViewState()
    object Failure: SongViewState()
    data class Success(val songs : List<Song>) :SongViewState()
}