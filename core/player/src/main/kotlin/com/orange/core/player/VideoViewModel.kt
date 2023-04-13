package com.orange.core.player


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.core.domain.use_cases.GetVideoTimestampUseCase
import com.orange.core.domain.use_cases.SaveVideoTimestampUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideoTimestampUseCase: GetVideoTimestampUseCase,
    private val saveVideoTimestampUseCase: SaveVideoTimestampUseCase,
) : ViewModel() {

    var isVideoStillPlaying = false

    private val _videoTimestampFlow: MutableStateFlow<Long> = MutableStateFlow(0)
    val videoTimestampFlow = _videoTimestampFlow.asSharedFlow()

    fun getVideoTimestamp(videoId: String) {
        viewModelScope.launch {
            val videoTimestampUseCase = getVideoTimestampUseCase(videoId)
            _videoTimestampFlow.emit(videoTimestampUseCase)
        }
    }

    fun saveVideoTimestamp(videoId: String, currentPosition: Long) {
        viewModelScope.launch {
            saveVideoTimestampUseCase(videoId, currentPosition)
        }
    }
}
