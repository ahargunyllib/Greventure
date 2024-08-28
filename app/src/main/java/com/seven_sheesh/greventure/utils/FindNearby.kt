package com.seven_sheesh.greventure.utils

import com.seven_sheesh.greventure.domain.model.Bubble

fun distanceInMeters(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val radiusOfEarth = 6371 * 1000
    val latDistance = Math.toRadians(lat2 - lat1)
    val lonDistance = Math.toRadians(lon2 - lon1)
    val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    return radiusOfEarth * c
}

fun find3ClosestBubbles(currentLat: Double, currentLon: Double, bubbles: List<Bubble>): List<Pair<Bubble, Double>> {
    return bubbles.map { bubble ->
        val distance = distanceInMeters(currentLat, currentLon, bubble.latitude, bubble.longitude)
        bubble to distance
    }.sortedBy { it.second }
        .take(3)
}


