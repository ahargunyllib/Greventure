package com.seven_sheesh.greventure.domain.model

data class AdvertisementType(
    val id: String = "",
    val businessName: String = "",
    val adContent: String = "",
    val location: String = "",
    val radius: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val createdAt: String = ""
)

object DummyAdvertisement {
    val advertisement = AdvertisementType(
        id = "1",
        businessName = "Local Cafe",
        adContent = "Enjoy a free coffee with your breakfast!",
        location = "-7.9545, 112.6155",  // String format for location near Universitas Brawijaya
        radius = 100,  // Radius in meters
        startTime = "2023-01-01 06:00:00",
        endTime = "2023-01-01 18:00:00",
        createdAt = "2023-01-01 05:00:00"
    )
}