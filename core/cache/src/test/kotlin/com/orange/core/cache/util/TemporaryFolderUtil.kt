package com.orange.core.cache.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import org.junit.rules.TemporaryFolder


object TemporaryFolderUtil {
    fun TemporaryFolder.testUserPreferencesDataStore(
        coroutineScope: CoroutineScope,
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = coroutineScope,
        produceFile = {
            newFile("file.preferences_pb")
        },
    )
}
