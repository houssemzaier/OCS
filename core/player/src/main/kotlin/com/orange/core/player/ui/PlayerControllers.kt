package com.orange.core.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerControllers(
    modifier: Modifier,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    isVisible: Boolean,
) {
    if (isVisible)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.5f)),
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { onPlay() },
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { onPause() },
            ) {
                Icon(
                    Icons.Default.Pause,
                    contentDescription = "Pause",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { onStop() },
            ) {
                Icon(
                    Icons.Default.Stop,
                    contentDescription = "Stop",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black,
                )
            }
        }
}

@Preview
@Composable
fun PlayerControllers_Prev() {
    PlayerControllers(Modifier, {}, {}, {}, true)
}
