package com.orange.core.domain.repositories

import com.orange.core.domain.models.Program

interface ProgramRepository {
    suspend fun findProgramsByQuery(query: String): List<Program>

    suspend fun findProgramDetails(program: Program): Program
}
