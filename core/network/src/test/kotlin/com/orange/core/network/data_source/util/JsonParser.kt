package com.orange.core.network.data_source.util

import com.orange.core.network.data_source.JsonFactory
import kotlinx.serialization.decodeFromString

object JsonParser {

    inline fun <reified T> String.toObject() =
        JsonFactory.create().decodeFromString<T>(this)
}
