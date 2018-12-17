package com.catalogit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.catalogit.data.CatalogDataRepository
import com.catalogit.data.model.MediaList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

//    private val catalogDataRepository: CatalogDataRepository = CatalogDataRepository(coroutineContext)
    private lateinit var catalogDataRepository: CatalogDataRepository

    // Using Dagger 2 to provide the UserRepository parameter.
    @Inject
    fun UserProfileViewModel(catalogDataRepository: CatalogDataRepository) {
        this.catalogDataRepository = catalogDataRepository
    }

    private val mediaList: LiveData<List<MediaList>>

    init {
        mediaList = catalogDataRepository.getMediaFromRepository()
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun reload() {
        catalogDataRepository.loadMediaFromServer()
    }

    fun getMediaList() = mediaList
    fun getNetworkErrors() = catalogDataRepository.networkErrors
}