package com.orange.core.network.models

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class DetailLinkParserTest {
    @Test
    fun `check parsing the detailLink in the right format`() {
        val detailLink = "/apps/v2/details/programme/ROUGEXXXXXXW0169949"
        val detailLinkParsed = DetailLinkParser.parse(detailLink)
        assertThat(detailLinkParsed).isEqualTo("ROUGEXXXXXXW0169949")
    }
}
