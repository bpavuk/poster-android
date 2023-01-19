package com.bpavuk.posterapp.ui.test

import com.bpavuk.posterapp.ui.PosterAppViewModel
import com.bpavuk.posterapp.ui.test.fake.FakePosterApiInterface
import com.bpavuk.posterapp.ui.test.fake.FakePosterRepository
import com.bpavuk.posterapp.ui.test.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PosterAppViewModelTest {
    private lateinit var viewModel: PosterAppViewModel
    private lateinit var repository: FakePosterRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun prepare() {
        repository = FakePosterRepository(FakePosterApiInterface)
        viewModel = PosterAppViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun viewModel_updatePostsOnInit_Success() = runTest {
        assertEquals(viewModel.uiState.postsList, repository.getOnlinePosts(0))
    }
}