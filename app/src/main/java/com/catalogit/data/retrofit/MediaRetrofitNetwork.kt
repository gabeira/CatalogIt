package com.catalogit.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by gabeira@gmail.com on 11/12/18.
 */
class MediaRetrofitNetwork {
    companion object {

        fun makeRetrofitService(url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}