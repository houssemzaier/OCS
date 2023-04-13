package com.orange.core.domain.use_cases

import com.orange.core.domain.repositories.VideoTimestampRepository
import javax.inject.Inject

class GetVideoTimestampUseCase @Inject constructor(
    private val videoTimestampRepository: VideoTimestampRepository,
) {
    suspend operator fun invoke(videoId: String): Long = videoTimestampRepository.get(videoId) ?: 0
}
