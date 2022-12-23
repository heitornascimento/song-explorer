package com.demo.song_discovery.view.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.song_discovery.view.state.NavigationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private  val _navigationState = MutableStateFlow<NavigationViewState>(NavigationViewState.SearchSongView)
    val navigationState = _navigationState
}