package com.catalogit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.catalogit.data.CatalogDataRepository
import com.catalogit.data.model.MediaList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val catalogDataRepository: CatalogDataRepository = CatalogDataRepository(coroutineContext)
    val mediaList: LiveData<List<MediaList>>

    init {
        mediaList = catalogDataRepository.mediaLiveData
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun reload() {
        catalogDataRepository.loadMediaFromServer()
    }

    fun getNetworkErrors() = catalogDataRepository.networkErrors
}