package com.banana.defacto.api

import com.banana.defacto.api.common.NumberType
import com.banana.defacto.api.dto.FactDto
import io.reactivex.rxjava3.core.Single

interface NumbersApiService {
    fun getNumberFact(number: Int, type: NumberType): Single<FactDto>

    fun getRandomNumberFact(type: NumberType): Single<FactDto>
}