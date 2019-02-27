package com.shereen.foxy.model.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shereen.foxy.model.room.ResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

        private const val baseUrl: String = "https://randomfox.ca/"

        fun makeRetrofitService(): FoxService {

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoxService::class.java)

//            return retrofit.create(FoxService::class.java)
        }

//        fun doFoxCall(): Deferred<Response<ResponseEntity>> {
//            return RetrofitFactory.create().foxCall()
//        }

}