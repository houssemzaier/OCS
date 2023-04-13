package com.orange.feature.home.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.orange.core.domain.models.Program
import com.orange.core.player.OrangePlayer
import com.orange.feature.home.R

/**
 * The content for the detail pane.
 */
@Composable
fun ProgramDetailPane(
    modifier: Modifier = Modifier,
    program: Program,
) {
    var isPlayerOn by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    isPlayerOn = true
                },

            ) {

            if (isPlayerOn) {
                OrangePlayer()
            } else {
                ConstraintLayout(
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .clip(RoundedCornerShape(8.dp)),


                    ) {
                    val (image, playIcon) = createRefs()

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(program.thumbnailUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Movie Thumbnail",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(
                                image,
                            ) {
                                this.top.linkTo(parent.top)
                                this.bottom.linkTo(parent.bottom)
                                this.start.linkTo(parent.start)
                                this.end.linkTo(parent.end)
                            },
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_play_media),
                        contentDescription = "Movie Thumbnail",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(36.dp)
                            .constrainAs(
                                playIcon,
                            ) {
                                this.top.linkTo(image.top)
                                this.bottom.linkTo(image.bottom)
                                this.start.linkTo(image.start)
                                this.end.linkTo(image.end)
                            },

                        colorFilter = ColorFilter.tint(Color.White.copy(alpha = .7f)),
                    )

                }
            }


        }


        Text(
            text = program.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
        )
        if (program.subtitle.isNotBlank()) {
            Text(
                text = program.subtitle,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
            )
        }


        Text(
            text = program.pitch,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
        )


    }


}

