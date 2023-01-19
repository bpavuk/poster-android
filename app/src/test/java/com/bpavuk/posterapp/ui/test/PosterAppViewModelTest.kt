package com.bpavuk.posterapp.ui.test

//class PosterAppViewModelTest {
//    private lateinit var viewModel: PosterAppViewModel
//    private lateinit var repository: FakePosterRepository
//
//    @get:Rule
//    val testDispatcher = TestDispatcherRule()
//
//    @Before
//    fun prepare() {
//        repository = FakePosterRepository(FakePosterApiInterface)
//        viewModel = PosterAppViewModel(repository)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun viewModel_updatePosts_Success() = runTest {
//        viewModel.updatePosts()
//        assertEquals(viewModel.uiState.postsList, repository.getOnlinePosts(0))
//    }
//}
// TODO("Impelent tests for updated PosterAppViewModel")