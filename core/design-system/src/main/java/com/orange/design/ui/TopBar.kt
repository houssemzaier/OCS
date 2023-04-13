package com.orange.design.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.orange.design.theme.DynamicTheme

@Composable
fun TopBar(shouldShowUpBtn: Boolean, onBackPress: (Boolean) -> Unit, isDarkTheme: Boolean) {
    TopAppBar(
        title = {
            val title = if (shouldShowUpBtn) {
                "Program Details"
            } else {
                "Program Search"
            }

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            val showToast = remember { mutableStateOf(false) }

            if (shouldShowUpBtn) {
                IconButton(
                    onClick = {
                        showToast.value = true
                        onBackPress(true)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = if (isDarkTheme) {
                            Color.White
                        } else {
                            Color.Black
                        },
                    )
                }
            }
            if (showToast.value) {
                // ShowToast("Hello from Composable!")
                showToast.value = false
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_TopBar_DarkTheme() {
    DynamicTheme(true) {
        TopBar(true, {}, true)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_TopBar() {
    DynamicTheme(false) {
        TopBar(true, {}, false)
    }
}
