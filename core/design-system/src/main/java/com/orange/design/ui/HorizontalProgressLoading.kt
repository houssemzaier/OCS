package com.orange.design.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.design.theme.DynamicTheme

@Composable
fun HorizontalProgressLoading() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(8.dp),
    ) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
private fun Prev_ProgressLoading() {
    DynamicTheme {
        HorizontalProgressLoading()
    }
}
