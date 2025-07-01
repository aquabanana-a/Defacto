package com.banana.defacto.api

import com.banana.defacto.api.dto.FactDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    companion object {
        private const val API_KEY_GET_NUMBER_FACT = "/{number}/{type}?json"
        private const val API_KEY_GET_RANDOM_NUMBER_FACT = "/random/{type}?json"
    }

    @GET(API_KEY_GET_NUMBER_FACT)
    fun getNumberFact(@Path("number") number: Int, @Path("type") type: String): Single<FactDto>

    @GET(API_KEY_GET_RANDOM_NUMBER_FACT)
    fun getRandomNumberFact(@Path("type") type: String): Single<FactDto>
}