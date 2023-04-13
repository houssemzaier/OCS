package com.orange.core.network.models

object DetailLinkParser {
    fun parse(detailLink: String): String {
        val delimiter = "/"
        val lastPart = if (detailLink.isNotEmpty() && detailLink.contains(delimiter)) {
            detailLink.substring(detailLink.lastIndexOf(delimiter) + 1)
        } else {
            ""
        }
        return lastPart
    }
}
