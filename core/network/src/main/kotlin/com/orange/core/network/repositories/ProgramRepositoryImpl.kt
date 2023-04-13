package com.orange.core.network.repositories

import com.orange.core.domain.models.Program
import com.orange.core.domain.repositories.ProgramRepository
import com.orange.core.network.data_source.NetworkApi
import com.orange.core.network.models.ApiSearchResponse
import com.orange.core.network.models.ApiSearchResponse.Companion.toProgramList
import com.orange.core.network.models.toProgramDetailed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ProgramRepositoryImpl @Inject constructor(
    private val api: NetworkApi,
) : ProgramRepository {
    override suspend fun findProgramsByQuery(query: String): List<Program> {
        val searchContents: ApiSearchResponse = api.searchContents("title=$query")
        return searchContents.toProgramList()
    }

    override suspend fun findProgramDetails(program: Program): Program {
        val apiDetailsResponse = api.getProgramDetails(program.detailLink)
        return apiDetailsResponse.toProgramDetailed(program)
    }
}
