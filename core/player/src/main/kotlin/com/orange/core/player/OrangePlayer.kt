package com.orange.core.player

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.orange.core.player.ui.PlayerControllers

@Composable
fun OrangePlayer() {
    Column {
        val myContext = LocalContext.current
        val activity = myContext as? ComponentActivity

        val videoViewModel: VideoViewModel = hiltViewModel()
        videoViewModel.getVideoTimestamp("ID")

        val context = LocalContext.current
        val timestamp = videoViewModel.videoTimestampFlow.collectAsStateWithLifecycle(0)
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                this.setMediaItem(createMediaItem())
                this.prepare()
                if (videoViewModel.isVideoStillPlaying) {
                    this.play()
                }
            }
        }
        val lifecycleOwner = LocalLifecycleOwner.current
        val lifecycle = lifecycleOwner.lifecycle
        val lifecycleObserver = remember(lifecycleOwner) {
            LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_PAUSE -> {
                        exoPlayer.pause()
                        videoViewModel.saveVideoTimestamp("ID", exoPlayer.currentPosition)
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        if (activity?.isChangingConfigurations == true) {
                            videoViewModel.isVideoStillPlaying = true
                        } else {
                            exoPlayer.release()
                            videoViewModel.isVideoStillPlaying = false
                        }
                    }
                    else -> Unit
                }
            }
        }

        DisposableEffect(lifecycle) {
            lifecycle.addObserver(lifecycleObserver)
            onDispose {
                lifecycle.removeObserver(lifecycleObserver)
            }
        }

        exoPlayer.seekTo(timestamp.value)

        var isVisible by remember {
            mutableStateOf(true)
        }
        ConstraintLayout(modifier = Modifier) {
            val (controllers, videoPlayer) = createRefs()
            DisposableEffect(
                AndroidView(
                    modifier = Modifier
                        .testTag("VideoPlayer")
                        .constrainAs(videoPlayer) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            isVisible = !isVisible
                        },
                    factory = {
                        PlayerView(context).apply {
                            useController = false
                            player = exoPlayer
                            layoutParams = FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                            )
                        }
                    },
                ),
            ) {
                onDispose {
                    exoPlayer.release()
                }
            }

            PlayerControllers(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("controllers")
                    .constrainAs(controllers) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onPlay = {
                    exoPlayer.prepare()
                    exoPlayer.play()
                },
                onPause = {
                    exoPlayer.pause()
                },
                onStop = {
                    exoPlayer.pause()
                    exoPlayer.seekTo(0)
                    exoPlayer.stop()
                    exoPlayer.prepare()
                },
                isVisible,
            )
        }
    }
}

private fun createMediaItem() = MediaItem.fromUri(Uri.parse("https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"))
