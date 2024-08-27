package com.seven_sheesh.greventure.di

import com.seven_sheesh.greventure.data.repository.AuthenticationRepositoryImpl
import com.seven_sheesh.greventure.data.repository.BubbleRepositoryImpl
import com.seven_sheesh.greventure.data.repository.CommentRepositoryImpl
import com.seven_sheesh.greventure.data.repository.BubblePhotoRepositoryImpl
import com.seven_sheesh.greventure.data.repository.BubbleSocialMediaRepositoryImpl
import com.seven_sheesh.greventure.data.repository.ThreadRepositoryImpl
import com.seven_sheesh.greventure.domain.repository.AuthenticationRepository
import com.seven_sheesh.greventure.domain.repository.BubbleRepository
import com.seven_sheesh.greventure.domain.repository.CommentRepository
import com.seven_sheesh.greventure.domain.repository.BubblePhotoRepository
import com.seven_sheesh.greventure.domain.repository.BubbleSocialMediaRepository
import com.seven_sheesh.greventure.domain.repository.ThreadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindBubbleRepository(
        bubbleRepositoryImpl: BubbleRepositoryImpl
    ): BubbleRepository

    @Binds
    @Singleton
    abstract fun bindCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ): CommentRepository

    @Binds
    @Singleton
    abstract fun bindBubblePhotoRepository(
        bubblePhotoRepositoryImpl: BubblePhotoRepositoryImpl
    ): BubblePhotoRepository

    @Binds
    @Singleton
    abstract fun bindBubbleSocialMediaRepository(
        bubbleSocialMediaRepositoryImpl: BubbleSocialMediaRepositoryImpl
    ): BubbleSocialMediaRepository

    @Binds
    @Singleton
    abstract fun bindThreadRepository(
        threadRepositoryImpl: ThreadRepositoryImpl
    ): ThreadRepository
}
