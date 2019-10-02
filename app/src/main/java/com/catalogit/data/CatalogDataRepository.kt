package com.catalogit.data

import androidx.lifecycle.MutableLiveData
import com.catalogit.data.model.MediaList
import com.catalogit.data.retrofit.MediaRetrofitNetwork
import com.catalogit.data.retrofit.MediaRetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class CatalogDataRepository(private val coroutineContext: CoroutineContext) {

    val mediaLiveData: MutableLiveData<List<MediaList>> = MutableLiveData()
    val networkErrors = MutableLiveData<String>()

    init {
        loadMediaFromServer()
    }

    fun loadMediaFromServer() {
        val service = MediaRetrofitNetwork
            .makeRetrofitService("https://s3-ap-southeast-2.amazonaws.com/video-catalogue/")
            .create(MediaRetrofitService::class.java)
        GlobalScope.launch(coroutineContext) {
            try {
                val request = service.requestMediaList()
                if (request.isSuccessful) {
                    mediaLiveData.value = request.body()
                } else {
                    networkErrors.postValue("Could not load ${request.errorBody()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                networkErrors.postValue(e.message)
            }
        }
    }
}