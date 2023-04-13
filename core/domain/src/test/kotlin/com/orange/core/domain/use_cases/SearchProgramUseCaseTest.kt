package com.orange.core.domain.use_cases

import com.orange.core.domain.repositories.ProgramRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SearchProgramUseCaseTest {

    private val mockProgramRepository = mock<ProgramRepository>()
    private lateinit var searchProgramUseCase: SearchProgramUseCase

    @Before
    fun setup() {
        searchProgramUseCase = SearchProgramUseCase(mockProgramRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun invokeTest() = runTest {
        searchProgramUseCase("programName")
        verify(mockProgramRepository).findProgramsByQuery("programName")
    }
}
