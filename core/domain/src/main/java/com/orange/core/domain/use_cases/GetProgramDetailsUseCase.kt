package com.orange.core.domain.use_cases

import com.orange.core.domain.models.Program
import com.orange.core.domain.repositories.ProgramRepository
import javax.inject.Inject

class GetProgramDetailsUseCase @Inject constructor(
    private val programRepository: ProgramRepository,
) {
    suspend operator fun invoke(program: Program): Program = programRepository.findProgramDetails(program)
}
