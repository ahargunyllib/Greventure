package com.seven_sheesh.greventure.domain.model

data class PremiumType(
    val id: String = "",
    val userId: String = "",
    val featureName: String = "",
    val purchaseDate: String = "",
    val expiryDate: String = ""
)

object DummyPremiumFeature {
    val premiumFeature = PremiumType(
        id = "1",
        userId = "1",
        featureName = "Ad-free Experience",
        purchaseDate = "2023-01-01 09:00:00",
        expiryDate = "2024-01-01 09:00:00"
    )
}