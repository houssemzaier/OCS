package com.orange.core.network.data_source

import kotlinx.serialization.json.Json

object JsonFactory {
    fun create(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }
}
