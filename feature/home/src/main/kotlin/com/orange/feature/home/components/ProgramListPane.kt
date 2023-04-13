package com.orange.feature.home.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.core.domain.models.Program
import com.orange.design.ui.ProgramItem
import com.orange.design.ui.ProgramUiModel
import com.orange.design.ui.SelectionVisibilityState

@Composable
fun ProgramListPane(
    list: List<Program>,
    selectionState: SelectionVisibilityState,
    onIndexClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val configuration = LocalConfiguration.current
        val isLandscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isLandscapeMode) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .then(
                        when (selectionState) {
                            SelectionVisibilityState.NoSelection -> Modifier
                            is SelectionVisibilityState.ShowSelection -> Modifier.selectableGroup()
                        },
                    ),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 4.dp),
                    ) {
                        Text(
                            text = "Programmes: (${list.size})",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        )
                    }
                }
                itemsIndexed(list) { index, program ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val interactionModifier = when (selectionState) {
                        SelectionVisibilityState.NoSelection -> {
                            Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = rememberRipple(),
                                onClick = { onIndexClick(index) },
                            )
                        }
                        is SelectionVisibilityState.ShowSelection -> {
                            Modifier.selectable(
                                selected = index == selectionState.selectedWordIndex,
                                interactionSource = interactionSource,
                                indication = rememberRipple(),
                                onClick = { onIndexClick(index) },
                            )
                        }
                    }
                    ProgramItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .then(interactionModifier)
                            .wrapContentHeight(),
                        program = list[index].toUiModel(),
                        isTablet = widthSizeClass == WindowWidthSizeClass.Medium,
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(8.dp),
                columns = GridCells.Fixed(2),
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 4.dp),
                    ) {
                        Text(
                            text = "Result (${list.size})",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 4.dp),
                    )
                }
                items(list.size) { index ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val interactionModifier = when (selectionState) {
                        SelectionVisibilityState.NoSelection -> {
                            Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = rememberRipple(),
                                onClick = { onIndexClick(index) },
                            )
                        }
                        is SelectionVisibilityState.ShowSelection -> {
                            Modifier.selectable(
                                selected = index == selectionState.selectedWordIndex,
                                interactionSource = interactionSource,
                                indication = rememberRipple(),
                                onClick = { onIndexClick(index) },
                            )
                        }
                    }
                    ProgramItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .then(interactionModifier)
                            .wrapContentHeight(),
                        program = list[index].toUiModel(),
                        isTablet = widthSizeClass == WindowWidthSizeClass.Medium,
                    )
                }
            }
        }
    }
}

fun Program.toUiModel(): ProgramUiModel =
    ProgramUiModel(
        id,
        title,
        subtitle,
        pitch,
        thumbnailUrl,
        imageUrl,
        detailLink,
    )
