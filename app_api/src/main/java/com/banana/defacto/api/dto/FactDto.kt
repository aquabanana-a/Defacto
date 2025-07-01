package com.banana.defacto.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactDto(
    @SerialName("text") val description: String,
    @SerialName("found") val found: Boolean,
    @SerialName("number") val number: Float,
    @SerialName("type") val type: String,
    @SerialName("date") val date: String? = null,
    @SerialName("year") val year: String? = null,
)