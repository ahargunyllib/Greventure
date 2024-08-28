package com.seven_sheesh.greventure.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Report(
    @SerialName("id") val id: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("bubble_id") val bubbleId: String,
    @SerialName("report_type") val reportType: ReportType,
    @SerialName("reason") val reason: String,
    @SerialName("created_at") val createdAt: String? = null
)