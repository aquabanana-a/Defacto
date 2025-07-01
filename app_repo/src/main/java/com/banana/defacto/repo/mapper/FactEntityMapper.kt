package com.banana.defacto.repo.mapper

import com.banana.defacto.data.Fact
import com.banana.defacto.db.entity.FactEntity

object FactEntityMapper {

    fun toModel(entity: FactEntity): Fact {
        return Fact(
            number = entity.number,
            type = NumberTypeMapper.toModel(entity.type),
            description = entity.description,
        )
    }

    fun toEntity(fact: Fact): FactEntity {
        return FactEntity(
            number = fact.number,
            type = NumberTypeMapper.toEntity(fact.type),
            description = fact.description,
        )
    }
}