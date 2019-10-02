package com.catalogit.data.retrofit

import com.catalogit.data.model.MediaList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
interface MediaRetrofitService {

    @GET("data.json")
    suspend fun requestMediaList(): Response<List<MediaList>>
}