package com.orange.core.network.data_source.util

import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

object FileHelper {

    fun fileToString(fileName: String): String {
        val resource: URL = javaClass.classLoader!!.getResource(fileName)
        return String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8)
    }
}
