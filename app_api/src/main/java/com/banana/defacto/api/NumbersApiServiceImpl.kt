package com.banana.defacto.api

import com.banana.defacto.api.common.NumberType
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class NumbersApiServiceImpl: NumbersApiService {
    companion object {
        private const val SERVICE_BASE_URL = "http://numbersapi.com/"
        private val SERVICE_MEDIA_TYPE = MediaType.get("application/json")
    }

    private val httpClient = OkHttpClient.Builder().run {
        addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory(SERVICE_MEDIA_TYPE))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(SERVICE_BASE_URL)
        .client(httpClient)
        .build()

    private val numberApi = retrofit.create(NumbersApi::class.java)

    override fun getNumberFact(number: Int, type: NumberType) = numberApi.getNumberFact(number, type.key)

    override fun getRandomNumberFact(type: NumberType) = numberApi.getRandomNumberFact(type.key)
}