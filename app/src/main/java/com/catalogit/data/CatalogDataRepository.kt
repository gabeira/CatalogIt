package com.catalogit.data

import androidx.lifecycle.MutableLiveData
import com.catalogit.data.model.MediaList
import com.catalogit.data.retrofit.MediaRetrofitNetwork
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class CatalogDataRepository(private val coroutineContext: CoroutineContext) {

    private val mediaLiveData: MutableLiveData<List<MediaList>> = MutableLiveData()
    val networkErrors = MutableLiveData<String>()

    init {
        loadMediaFromServer()
    }

    fun loadMediaFromServer() {
        val service = MediaRetrofitNetwork.makeRetrofitService()
        GlobalScope.launch(coroutineContext) {
            try {
                val request = service.requestMediaList()
                val response = request.await()
                if (request.isCompleted) {
                    mediaLiveData.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
                networkErrors.postValue(e.message)
            }
        }
    }

    fun getMediaFromRepository() = mediaLiveData
}