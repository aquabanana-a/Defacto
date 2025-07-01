package com.banana.defacto.db.common

enum class NumberType(val id: Int) {
    Trivia(1),
    Year(2),
    Math(3);

    companion object {
        fun fromId(id: Int): NumberType =
            entries.firstOrNull { it.id == id }
                ?: throw IllegalArgumentException("Unknown id: $id")
    }
}