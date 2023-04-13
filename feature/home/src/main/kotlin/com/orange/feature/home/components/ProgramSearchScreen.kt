package com.orange.feature.home.components

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.orange.design.ui.TopBar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProgramSearchScreen(
    isShowingDetails: StateFlow<Boolean>,
    onBackPress: (Boolean) -> Unit = {},
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
) {
    val isDetailsOn: Boolean by isShowingDetails.collectAsState()

    val isDarkTheme = isSystemInDarkTheme()

    val widthSizeClass by rememberUpdatedState(windowSizeClass.widthSizeClass)

    Scaffold(
        topBar = {
            val context = LocalContext.current

            TopBar(
                isDetailsOn && (widthSizeClass == WindowWidthSizeClass.Compact),
                {
                    onBackPress(it)
                    (context as? Activity)?.onBackPressed()
                },
                isDarkTheme,
            )
        },
        content = { innerPadding ->
            ProgramSearchScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                windowSizeClass = windowSizeClass,
                displayFeatures = displayFeatures,
            )
        },
    )
}

@Preview
@Composable
fun ProgramSearchScreen_Preview() {
    val isShowingDetails: StateFlow<Boolean> = MutableStateFlow(true)
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(100.dp, 100.dp))
    val displayFeatures = listOf<DisplayFeature>()
    ProgramSearchScreen(isShowingDetails, {}, windowSizeClass, displayFeatures)
}
