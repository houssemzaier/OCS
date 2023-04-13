package com.orange.design.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProgramItem(modifier: Modifier, program: ProgramUiModel, isTablet: Boolean = false) {
    Card(
        modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(8.dp)),
                painter = rememberAsyncImagePainter(program.thumbnailUrl),
                contentDescription = "Un programme",
                contentScale = ContentScale.FillWidth,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 4.dp),
                text = program.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                text = program.subtitle,
                fontSize = 14.sp,
            )
        }
    }
}

@Preview
@Composable
fun ProgramItem_Prev() {
    /* ktlint-disable max-line-length */
    ProgramItem(
        Modifier.background(Color.Cyan),
        ProgramUiModel(
            id = "1",
            title = "One Title",
            subtitle = "One subtitle !",
            pitch = "Nour, infirmière de formation, est embauchée dans l'usine chimique où son père est délégué syndical. Lorsqu'une journaliste commence à poser des questions sur les rejets toxiques de l'usine, Nour fait le lien avec l'état de santé des ouvriers qui passent à son cabinet. Pourtant, elle est surprise de constater que la plupart gardent le silence, de crainte de perdre leur emploi. La curiosité de la jeune femme la pousse à se demande le rôle que peut bien jouer son père, lui qui affirme catégoriquement qu'il n'y a aucun problème. Drame social, familial et écologique, \"Rouge\" prend appui sur un brillant casting pour raconter un récit hélas inspirée d'une histoire vraie.",
            thumbnailUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=small",
            imageUrl = "/data_plateforme/program/54396/origin_rougexxxxxxw0169949_35i06.jpg?size=large",
            detailLink = "/apps/v2/details/programme/ROUGEXXXXXXW0169949",
        ),
    )
}
/* ktlint-enable max-line-length */

data class ProgramUiModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val pitch: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val detailLink: String,
)
