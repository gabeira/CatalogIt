package com.catalogit.data.retrofit

import com.catalogit.data.model.MediaList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
interface MediaRetrofitService {

    @GET("data.json")
    fun requestMediaList(): Deferred<List<MediaList>>
}