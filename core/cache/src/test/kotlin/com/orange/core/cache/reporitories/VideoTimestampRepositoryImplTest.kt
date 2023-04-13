package com.orange.core.cache.reporitories

import com.google.common.truth.Truth.assertThat
import com.orange.core.cache.util.TemporaryFolderUtil.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class VideoTimestampRepositoryImplTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: VideoTimestampRepositoryImpl

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        subject = VideoTimestampRepositoryImpl(tmpFolder.testUserPreferencesDataStore(testScope))
    }

    @Test
    fun saveThenGetTest() = testScope.runTest {
        subject.save("111", 1_000)
        subject.save("112", 1_001)
        subject.save("111", 2_000)

        assertThat(subject.data.first().asMap().size).isEqualTo(2)

        assertThat(subject.get("112")).isEqualTo(1_001)
        assertThat(subject.get("111")).isEqualTo(2_000)

    }

}
