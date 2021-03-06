package com.example.mayank.myplaygame.network

import com.example.mayank.payumoneydemo.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    @PublishedApi
    internal var retrofit: Retrofit


    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val httpClient = OkHttpClient.Builder()
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    inline fun <reified T> getService(): T {
        return retrofit.create(T::class.java)
    }
}