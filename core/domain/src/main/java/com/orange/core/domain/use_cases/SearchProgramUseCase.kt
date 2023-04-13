package com.orange.core.domain.use_cases

import com.orange.core.domain.models.Program
import com.orange.core.domain.repositories.ProgramRepository
import javax.inject.Inject

class SearchProgramUseCase @Inject constructor(
    private val programRepository: ProgramRepository,
) {
    suspend operator fun invoke(programName: String): List<Program> = programRepository.findProgramsByQuery(programName)
}
