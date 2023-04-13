package com.orange.feature.video

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.orange.core.player.OrangePlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.Red) {
                OrangePlayer()
            }
        }
    }
}
