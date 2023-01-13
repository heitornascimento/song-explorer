package com.demo.song_discovery.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.demo.song_discovery.R
import com.demo.song_discovery.databinding.ActivitySongExplorerBinding
import com.demo.song_discovery.view.state.NavigationViewState
import com.demo.song_discovery.view.viewmodel.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SongExplorerActivity : AppCompatActivity() {

    val viewModel: NavigationViewModel by viewModels()
    private lateinit var binding: ActivitySongExplorerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongExplorerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserverNavigation()
    }

    private fun initObserverNavigation(){
        lifecycleScope.launchWhenResumed {
            val navController = findNavController(R.id.nav_host_fragment)
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.navigationState.collect{ navState ->
                    with(navController){
                        when(navState){
                            NavigationViewState.SearchSongView -> {}
                            is NavigationViewState.SongDetailsView -> navigate(R.id.action_searchTrackFragment_to_songDetailsFragment)
                        }
                    }
                }
            }
        }
    }

}