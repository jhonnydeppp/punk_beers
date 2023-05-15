package com.jhonny.punkbeer

import com.jhonny.punkbeer.domain.usecases.GetBeersByNameUseCase
import com.jhonny.punkbeer.domain.usecases.GetBeersUseCase
import com.jhonny.punkbeer.presenter.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * This is a good test
 *
 * This is a complex test
 */
class ExampleUnitTest {

    @RelaxedMockK
    private lateinit var getBeersUseCase: GetBeersUseCase
    @RelaxedMockK
    private lateinit var getBeersByNameUseCase: GetBeersByNameUseCase

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(getBeersUseCase, getBeersByNameUseCase)
    }

    @Test
    fun testPage() {
        testDispatcher.run{
            val page = viewModel.page
            viewModel.geBeers()
            val page2 = viewModel.page
            assert(page < page2)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}