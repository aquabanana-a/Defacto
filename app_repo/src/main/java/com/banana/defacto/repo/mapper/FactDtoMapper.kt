package com.banana.defacto.repo.mapper

import com.banana.defacto.api.dto.FactDto
import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType

object FactDtoMapper {

    fun transformToModel(dto: FactDto): Fact {
        return Fact(
            number = dto.number.toInt(),
            type = NumberType.fromKey(dto.type),
            description = dto.description,
        )
    }
}