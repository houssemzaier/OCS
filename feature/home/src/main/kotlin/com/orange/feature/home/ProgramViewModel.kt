package com.orange.feature.home

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.core.domain.models.Program
import com.orange.core.domain.use_cases.GetProgramDetailsUseCase
import com.orange.core.domain.use_cases.SearchProgramUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(
    private val searchProgramUseCase: SearchProgramUseCase,
    private val getProgramDetailsUseCase: GetProgramDetailsUseCase,
) : ViewModel() {

    private val _onBackPress = MutableStateFlow(false)
    val onBackPress: StateFlow<Boolean> = _onBackPress.asStateFlow()

    private val _isShowingDetails = MutableStateFlow(false)
    val isShowingDetails: StateFlow<Boolean> = _isShowingDetails.asStateFlow()

    private val _searchTextFieldValue = MutableStateFlow(TextFieldValue(""))
    val searchTextFieldValue: StateFlow<TextFieldValue> = _searchTextFieldValue.asStateFlow()

    private val _programList = MutableStateFlow<List<Program>>(emptyList())
    val programList: StateFlow<List<Program>> = _programList.asStateFlow()

    private val _programDetailedState = MutableStateFlow<ProgramDetailedState>(ProgramDetailedState.Loading)
    val programDetailedState: SharedFlow<ProgramDetailedState> = _programDetailedState.asSharedFlow()

    fun updateIsShowingDetails(isShowingDetails: Boolean) {
        _isShowingDetails.value = isShowingDetails
    }

    fun onBackPress(onBackPress: Boolean) {
        _onBackPress.value = onBackPress
    }

    fun updateSearchTextFieldValue(textFieldValue: TextFieldValue) {
        _searchTextFieldValue.value = textFieldValue
    }

    fun searchProgram(search: String) {
        viewModelScope.launch {
            if (search.isNotEmpty() && search.length > 3) {
                try {
                    val programList = searchProgramUseCase(search)
                    _programList.value = programList

                } catch (e: Exception) {
                    //todo: handle Error
                }
            }
        }
    }

    fun searchProgramDetails(program: Program) {
        viewModelScope.launch {
            try {
                val programWithPitchFilled = getProgramDetailsUseCase(program)
                _programDetailedState.value = ProgramDetailedState.Success(programWithPitchFilled)
            } catch (e: Exception) {
                //todo: handle Error
            }
        }
    }

    sealed interface ProgramDetailedState {
        object Loading : ProgramDetailedState
        data class Success(val program: Program) : ProgramDetailedState
    }
}
