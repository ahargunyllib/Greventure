package com.seven_sheesh.greventure.domain.repository

import com.seven_sheesh.greventure.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getAllComments(): Flow<Pair<String, List<Comment>>>
    fun getCommentById(commentId: String): Flow<Pair<String, Comment?>>
    fun getCommentsByThreadId(threadId: String): Flow<Pair<String, List<Comment>>>
    fun upsertComment(comment: Comment): Flow<String>
    fun deleteComment(commentId: String): Flow<String>
}
