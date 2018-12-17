package com.catalogit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catalogit.data.CatalogDataRepository
import com.jraska.livedata.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import kotlin.coroutines.CoroutineContext

class LiveDataTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CatalogDataRepository
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    @Before
    fun before() {
        val mockClass = Mockito.mock(CoroutineContext::class.java)
        viewModel = CatalogDataRepository(mockClass)
    }

    @Test
    fun directAssertion() {
        viewModel.getMediaFromRepository()
            .test()
            .assertHasValue()
    }
}