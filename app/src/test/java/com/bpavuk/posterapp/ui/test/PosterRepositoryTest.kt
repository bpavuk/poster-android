package com.bpavuk.posterapp.ui.test

import com.bpavuk.posterapp.data.DefaultPosterRepository
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.ui.test.fake.Datasource
import com.bpavuk.posterapp.ui.test.fake.FakePosterApiInterface
import com.bpavuk.posterapp.ui.test.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PosterRepositoryTest {
    private lateinit var posterApi: FakePosterApiInterface
    private lateinit var repository: PosterRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun prepare() {
        posterApi = FakePosterApiInterface
        repository = DefaultPosterRepository(posterApi)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun repository_getOnlinePosts_Success() = runTest {
        val postsList = repository.getOnlinePosts()
        assertEquals(postsList, Datasource.fakePosts)
    }
}