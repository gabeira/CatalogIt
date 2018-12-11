package com.catalogit.data.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaRetrofitNetwork {
    companion object {

        private const val URL_RETROFIT = "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/"
        private var gsonConverterFactory: Converter.Factory = GsonConverterFactory.create()

        fun makeRetrofitService(): MediaRetrofitService {
            return Retrofit.Builder()
                .baseUrl(URL_RETROFIT)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(MediaRetrofitService::class.java)
        }
    }
}