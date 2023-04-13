package com.orange.core.domain.repositories

interface VideoTimestampRepository {
    suspend fun save(videoId: String, timestamp: Long)
    suspend fun get(videoId: String): Long?
}
