package com.catalogit

import androidx.lifecycle.MutableLiveData
import com.catalogit.data.CatalogDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.*

class RepositoryUnitTest {

    @Test
    fun testNoMock() {
        val catalogDataRepository = CatalogDataRepository(Job() + Dispatchers.Main)

        catalogDataRepository.loadMediaFromServer()
        Assert.assertNotNull(catalogDataRepository.getMediaFromRepository())
    }

    @Test
    fun testCoroutineMock() = runBlocking<Unit> {
        val mockClass = mock(CatalogDataRepository::class.java)
        `when`(mockClass.getMediaFromRepository()).thenReturn(MutableLiveData())
        assertThat(mockClass.getMediaFromRepository(), IsEqual(MutableLiveData()))
        verify(mockClass).getMediaFromRepository()
    }
}