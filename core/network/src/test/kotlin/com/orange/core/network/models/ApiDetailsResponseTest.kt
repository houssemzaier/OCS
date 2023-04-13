package com.orange.core.network.models

import com.google.common.truth.Truth
import com.orange.core.domain.models.Program
import com.orange.core.network.data_source.util.FileHelper
import com.orange.core.network.data_source.util.JsonParser.toObject
import org.junit.Test

class ApiDetailsResponseTest {

    @Test
    fun `check Parser is able to parse a json response and map it to domain object`() {
        val jsonString = FileHelper.fileToString("program-details.json")

        val apiDetailsResponse: ApiDetailsResponse = jsonString.toObject()
        val programUpdated = apiDetailsResponse.toProgramDetailed(
            Program(
                id = "ROUGEXXXXXXW0169949",
                title = "ROUGE",
                subtitle = "drame",
                pitch = "",
                thumbnailUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=small",
                imageUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=large",
                detailLink = "/apps/v2/details/programme/ROUGEXXXXXXW0169949",
            ),
        )

        val pitch = """
            Nour, infirmière de formation, est embauchée dans l'usine chimique où son père est délégué syndical. Lorsqu'une journaliste commence à poser des questions sur les rejets toxiques de l'usine, Nour fait le lien avec l'état de santé des ouvriers qui passent à son cabinet. Pourtant, elle est surprise de constater que la plupart gardent le silence, de crainte de perdre leur emploi. La curiosité de la jeune femme la pousse à se demande le rôle que peut bien jouer son père, lui qui affirme catégoriquement qu'il n'y a aucun problème. Drame social, familial et écologique, "Rouge" prend appui sur un brillant casting pour raconter un récit hélas inspirée d'une histoire vraie.
        """.trimIndent()

        Truth.assertThat(programUpdated).isEqualTo(
            Program(
                id = "ROUGEXXXXXXW0169949",
                title = "ROUGE",
                subtitle = "drame",
                pitch = pitch,
                thumbnailUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=small",
                imageUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=large",
                detailLink = "/apps/v2/details/programme/ROUGEXXXXXXW0169949",
            ),
        )
    }
}
