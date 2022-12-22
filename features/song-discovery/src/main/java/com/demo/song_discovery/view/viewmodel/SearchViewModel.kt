package com.demo.song_discovery.view.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.demo.song_discovery.di.MainDispatcher
import com.demo.song_discovery.domain.SearchSongs
import com.demo.song_discovery.view.state.SongViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val searchSongs: SearchSongs
) : ViewModel() {

    @VisibleForTesting
    internal val stateFlow = MutableStateFlow<SongViewState>(SongViewState.Idle)
    val state: StateFlow<SongViewState> = stateFlow


    fun querySong(query: String) {
        if (state.value == SongViewState.Searching) return

        viewModelScope.launch(dispatcher) {
            val queryEncoded =  URLEncoder.encode(query, "utf-8")

            runCatching {
                stateFlow.tryEmit(SongViewState.Searching)
                searchSongs(query)
            }
                .onSuccess { stateFlow.tryEmit(SongViewState.Success(it)) }
                .onFailure { error : Throwable ->
                    Log.d("itunes", error.message!!)
                    stateFlow.tryEmit(SongViewState.Failure)
                }

        }
    }
}