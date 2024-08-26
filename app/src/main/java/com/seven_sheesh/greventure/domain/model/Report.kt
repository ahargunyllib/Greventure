package com.seven_sheesh.greventure.domain.model

data class Report(
    val id: Int,
    val userId: String,
    val bubbleId: String,
    val reportType: ReportType,
    val reason: String,
    val createdAt: String? = null
)