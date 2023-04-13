package com.orange.core.cache.reporitories

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.orange.core.domain.repositories.VideoTimestampRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoTimestampRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : VideoTimestampRepository {

    @VisibleForTesting
    val data = dataStore.data

    override suspend fun save(videoId: String, timestamp: Long) {
        dataStore.edit { preferences ->
            preferences[getTimestampKey(videoId)] = timestamp
        }
    }

    override suspend fun get(videoId: String): Long? {
        return dataStore.data
            .map { preferences ->
                preferences[getTimestampKey(videoId)]
            }
            .catch { exception ->
                if (exception is IOException) {
                    emit(null)
                } else {
                    throw exception
                }
            }
            .firstOrNull()
    }

    private fun getTimestampKey(videoId: String): Preferences.Key<Long> {
        return longPreferencesKey("video_$videoId")
    }
}
