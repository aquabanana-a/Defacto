package com.banana.defacto.repo.mapper

import com.banana.defacto.data.NumberType

object NumberTypeMapper {

    fun toDto(type: NumberType): com.banana.defacto.api.common.NumberType {
        return when (type) {
            NumberType.Trivia -> com.banana.defacto.api.common.NumberType.Trivia
            NumberType.Math -> com.banana.defacto.api.common.NumberType.Math
            NumberType.Year -> com.banana.defacto.api.common.NumberType.Year
        }
    }

    fun toEntity(type: NumberType): com.banana.defacto.db.common.NumberType {
        return when (type) {
            NumberType.Trivia -> com.banana.defacto.db.common.NumberType.Trivia
            NumberType.Math -> com.banana.defacto.db.common.NumberType.Math
            NumberType.Year -> com.banana.defacto.db.common.NumberType.Year
        }
    }

    fun toModel(type: com.banana.defacto.api.common.NumberType): NumberType {
        return when(type) {
            com.banana.defacto.api.common.NumberType.Trivia -> NumberType.Trivia
            com.banana.defacto.api.common.NumberType.Math -> NumberType.Math
            com.banana.defacto.api.common.NumberType.Year -> NumberType.Year
        }
    }

    fun toModel(type: com.banana.defacto.db.common.NumberType): NumberType {
        return when (type) {
            com.banana.defacto.db.common.NumberType.Trivia -> NumberType.Trivia
            com.banana.defacto.db.common.NumberType.Math -> NumberType.Math
            com.banana.defacto.db.common.NumberType.Year -> NumberType.Year
        }
    }
}