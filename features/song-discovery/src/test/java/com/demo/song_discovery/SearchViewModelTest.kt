package com.demo.song_discovery

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.demo.song_discovery.domain.SearchSongs
import com.demo.song_discovery.view.state.SongViewState
import com.demo.song_discovery.view.viewmodel.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class SearchViewModelTest {
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = TestCoroutineDispatcher()

    private val searchSongUseCaseMock: SearchSongs = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val viewModel = SearchViewModel(testDispatcher, searchSongUseCaseMock)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should search for a song`() {
        runTest {
            coEvery { searchSongUseCaseMock.invoke(any()) } returns mySongs
            viewModel.querySong("Me and my bass guitar")

            viewModel.stateFlow.test {
                assertEquals(SongViewState.Success(mySongs), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            coVerify {  searchSongUseCaseMock.invoke(any()) }
        }
    }

}