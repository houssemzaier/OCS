package com.orange.core.domain.use_cases

import com.orange.core.domain.repositories.VideoTimestampRepository
import javax.inject.Inject

class SaveVideoTimestampUseCase @Inject constructor(
    private val videoTimestampRepository: VideoTimestampRepository,
) {
    suspend operator fun invoke(videoId: String, timestamp: Long) = videoTimestampRepository.save(videoId, timestamp)
}
