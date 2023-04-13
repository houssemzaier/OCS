package com.orange.core.network.models

import com.google.common.truth.Truth.assertThat
import com.orange.core.domain.models.Program
import com.orange.core.network.data_source.util.FileHelper.fileToString
import com.orange.core.network.data_source.util.JsonParser.toObject
import com.orange.core.network.models.ApiSearchResponse.Companion.toProgramList
import org.junit.Test

class ApiSearchResponseTest {
    @Test
    fun `check Parser is able to parse a json response and map it to domain object`() {
        val jsonString = fileToString("search-contents-by-title.json")

        val apiSearchResponse: ApiSearchResponse = jsonString.toObject()
        val programList: List<Program> = apiSearchResponse.toProgramList()

        assertThat(programList).isEqualTo(
            listOf(
                Program(
                    id = "ROUGEXXXXXXW0169949",
                    title = "ROUGE",
                    subtitle = "drame",
                    pitch = "",
                    thumbnailUrl = "https://statics.ocs.fr/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=small",
                    imageUrl = "https://statics.ocs.fr/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=large",
                    detailLink = "ROUGEXXXXXXW0169949",
                ),
                Program(
                    id = "ROUGEBAISERW0195135",
                    title = "ROUGE BAISER",
                    subtitle = "classique, comédie romantique, drame",
                    pitch = "",
                    thumbnailUrl = "https://statics.ocs.fr/data_plateforme/program/53891/origin_rougebaiserw0195135_gevsu.jpg?size=small",
                    imageUrl = "https://statics.ocs.fr/data_plateforme/program/53891/origin_rougebaiserw0195135_gevsu.jpg?size=large",
                    detailLink = "ROUGEBAISERW0195135",
                ),
                Program(
                    id = "ALLIANCESROW0189484",
                    title = "ALLIANCES ROUGE SANG",
                    subtitle = "drame, policier",
                    pitch = "",
                    thumbnailUrl = "https://statics.ocs.fr/data_plateforme/program/51957/origin_alliancesrow0189484_0i73f.jpg?size=small",
                    imageUrl = "https://statics.ocs.fr/data_plateforme/program/51957/origin_alliancesrow0189484_0i73f.jpg?size=large",
                    detailLink = "ALLIANCESROW0189484",
                ),
            ),
        )
    }
}
