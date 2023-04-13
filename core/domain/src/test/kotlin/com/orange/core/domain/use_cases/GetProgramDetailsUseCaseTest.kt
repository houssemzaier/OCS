package com.orange.core.domain.use_cases

import com.orange.core.domain.models.Program
import com.orange.core.domain.repositories.ProgramRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class GetProgramDetailsUseCaseTest {

    private val mockProgramRepository = mock<ProgramRepository>()
    private lateinit var getProgramDetailsUseCase: GetProgramDetailsUseCase

    @Before
    fun setup() {
        getProgramDetailsUseCase = GetProgramDetailsUseCase(mockProgramRepository)
    }

    @Test
    fun invokeTest() = runTest {
        val mockProgram = mock<Program>()
        getProgramDetailsUseCase(mockProgram)
        verify(mockProgramRepository).findProgramDetails(mockProgram)
    }
}
