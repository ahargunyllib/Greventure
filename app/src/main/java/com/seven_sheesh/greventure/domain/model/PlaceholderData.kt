package com.seven_sheesh.greventure.domain.model

import java.time.ZonedDateTime
import java.util.Random
import java.util.UUID

object PlaceholderData {
    val user1 = User(
        id = "user_123",
        name = "John Doe",
        email = "john.doe@example.com",
        phoneNum = "123-456-7890",
        profilePictureUrl = "http://example.com/profile.jpg",
        createdAt = ZonedDateTime.now().toString(),
        updatedAt = ZonedDateTime.now().toString()
    )

    val user2 = User(
        id = "user_456",
        name = "Jane Smith",
        email = "jane.smith@example.com",
        phoneNum = "987-654-3210",
        profilePictureUrl = "http://example.com/profile2.jpg",
        createdAt = ZonedDateTime.now().toString(),
        updatedAt = ZonedDateTime.now().toString()
    )

    val user3 = User(
        id = "user_789",
        name = "Alice Johnson",
        email = "alice.johnson@example.com",
        phoneNum = "555-123-4567",
        profilePictureUrl = "http://example.com/profile3.jpg",
        createdAt = ZonedDateTime.now().toString(),
        updatedAt = ZonedDateTime.now().toString()
    )

    // Bubble
    val bubble1 = Bubble(
        id = "bubble_123",
        userId = user1.id,
        title = "Community Event",
        description = "A fun event for everyone in the community!",
        type = BubbleType.Event,
        eventType = EventType.Komunitas,
        latitude = 37.7749,
        longitude = -122.4194,
        startTime = ZonedDateTime.now().toString(),
        duration = java.time.Duration.ofHours(2).toString(),
        phoneNumber = "123-456-7890",
        createdAt = ZonedDateTime.now().toString(),
        updatedAt = ZonedDateTime.now().toString()
    )

    val bubble2 = Bubble(
        id = "bubble_456",
        userId = user2.id,
        title = "Art Workshop",
        description = "Join us for a creative art workshop!",
        type = BubbleType.Event,
        eventType = EventType.Komunitas,
        latitude = 34.0522,
        longitude = -118.2437,
        startTime = ZonedDateTime.now().plusDays(1).toString(),
        duration = java.time.Duration.ofHours(3).toString(),
        phoneNumber = "987-654-3210",
        createdAt = ZonedDateTime.now().toString(),
        updatedAt = ZonedDateTime.now().toString()
    )

    // Bubble Photos
    val bubblePhoto1 = BubblePhoto(
        id = 1,
        bubbleId = bubble1.id,
        url = "http://example.com/photo1.jpg",
        createdAt = ZonedDateTime.now().toString()
    )

    val bubblePhoto2 = BubblePhoto(
        id = 2,
        bubbleId = bubble2.id,
        url = "http://example.com/photo2.jpg",
        createdAt = ZonedDateTime.now().toString()
    )

    // Bubble Social Media
    val bubbleSocialMedia1 = BubbleSocialMedia(
        id = 1,
        bubbleId = bubble1.id,
        content = "Check out our event on Instagram!",
        type = SocialMedia.Instagram,
        createdAt = ZonedDateTime.now().toString()
    )

    val bubbleSocialMedia2 = BubbleSocialMedia(
        id = 2,
        bubbleId = bubble2.id,
        content = "We're live-tweeting the event!",
        type = SocialMedia.Tiktok,
        createdAt = ZonedDateTime.now().toString()
    )

    // Threads
    val thread1 = Thread(
        id = "thread_123",
        bubbleId = bubble1.id,
        userId = user1.id,
        content = "This event sounds great!",
        createdAt = ZonedDateTime.now().toString()
    )

    val thread2 = Thread(
        id = "thread_456",
        bubbleId = bubble2.id,
        userId = user3.id,
        content = "Can't wait to attend the workshop!",
        createdAt = ZonedDateTime.now().toString()
    )

    // Comments
    val comment1 = Comment(
        id = "comment_123",
        threadId = thread1.id,
        userId = user2.id,
        content = "I'm looking forward to it too!",
        createdAt = ZonedDateTime.now().toString()
    )

    val comment2 = Comment(
        id = "comment_456",
        threadId = thread2.id,
        userId = user1.id,
        content = "This workshop is going to be amazing!",
        createdAt = ZonedDateTime.now().toString()
    )

    // Bookmarks
    val bookmark1 = Bookmark(
        id = "bookmark_123",
        userId = user3.id,
        bubbleId = bubble1.id,
        isStarted = true,
        createdAt = ZonedDateTime.now().toString()
    )

    val bookmark2 = Bookmark(
        id = "bookmark_456",
        userId = user1.id,
        bubbleId = bubble2.id,
        isStarted = false,
        createdAt = ZonedDateTime.now().toString()
    )

    // Reports
    val report1 = Report(
        id = 1,
        userId = user2.id,
        bubbleId = bubble1.id,
        reportType = ReportType.FRAUD,
        reason = "This event seems suspicious.",
        createdAt = ZonedDateTime.now().toString()
    )

    val report2 = Report(
        id = 2,
        userId = user3.id,
        bubbleId = bubble2.id,
        reportType = ReportType.FRAUD,
        reason = "Some content is offensive.",
        createdAt = ZonedDateTime.now().toString()
    )

    val review1 = Review(
        id = 1,
        userId = user1.id,
        bubbleId = bubble1.id,
        star = 5,
        content = "Amazing event!",
        createdAt = ZonedDateTime.now().toString()
    )

    val review2 = Review(
        id = 2,
        userId = user3.id,
        bubbleId = bubble2.id,
        star = 4,
        content = "Great workshop, learned a lot!",
        createdAt = ZonedDateTime.now().toString()
    )

    val bubbleEmpty = Bubble(
        id = UUID.randomUUID().toString(),
        userId = "",
        title = "",
        description = "",
        type = BubbleType.Event,
        eventType = EventType.Komunitas,
        latitude = 0.0,
        longitude = 0.0,
        startTime = java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX")),
        duration = "00:00:00",
        phoneNumber = "",
        createdAt = java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX")),
        updatedAt = java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"))
    )

    val bubblePhotoEmpty = BubblePhoto(
        id = kotlin.random.Random.nextInt(0, 128).toByte().toInt(),
        bubbleId = "",
        url = "",
        createdAt = java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"))
    )

    val bubbleSocialMediaEmpty = BubbleSocialMedia(
        id = kotlin.random.Random.nextInt(0, 128).toByte().toInt(),
        bubbleId = "",
        content = "",
        type = SocialMedia.Instagram,
        createdAt = java.time.OffsetDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"))
    )
}
