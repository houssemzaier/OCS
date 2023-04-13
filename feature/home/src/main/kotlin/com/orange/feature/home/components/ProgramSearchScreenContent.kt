package com.orange.feature.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.orange.core.domain.models.Program
import com.orange.design.ui.HorizontalProgressLoading
import com.orange.design.ui.ListDetail
import com.orange.design.ui.SearchTextField
import com.orange.design.ui.SelectionVisibilityState
import com.orange.feature.home.ProgramViewModel
import com.orange.feature.home.ProgramViewModel.ProgramDetailedState
import com.orange.feature.home.ProgramViewModel.ProgramDetailedState.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun ProgramSearchScreenContent(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
) {
    Column(modifier) {
        val viewModel: ProgramViewModel = hiltViewModel()

        val scope = rememberCoroutineScope()
        var isLoading by remember { mutableStateOf(false) }

        val search: (String) -> Unit = {
            scope.launch {
                viewModel.searchProgram(it)
                if (it.length > 3) {
                    isLoading = true
                }
            }
        }

        val getMovieDetails: (Program) -> Unit = {
            scope.launch {
                viewModel.searchProgramDetails(it)
            }

        }

        val programList by viewModel.programList.collectAsState()
        val programDetailedState by viewModel.programDetailedState.collectAsState(Loading)

        val textFieldValueFlow = remember { MutableStateFlow(TextFieldValue("")) }
        val searchTextFieldValue by viewModel.searchTextFieldValue.collectAsState()

        var selectedItemIndex: Int? by rememberSaveable { mutableStateOf(null) }


        // Query for the current window size class
        val widthSizeClass by rememberUpdatedState(windowSizeClass.widthSizeClass)

        var isDetailOpen by rememberSaveable { mutableStateOf(false) }


        var isDetailApiCalled: Boolean by rememberSaveable { mutableStateOf(false) }
        var isDetailApiCalledIndex: Int? by rememberSaveable { mutableStateOf(null) }


        LaunchedEffect(key1 = isDetailOpen) {
            viewModel.updateIsShowingDetails(isDetailOpen)
        }


        LaunchedEffect(key1 = isLoading) {
            if (isLoading) {
                delay(3000)
                isLoading = false
            }
        }

        LaunchedEffect(textFieldValueFlow) {
            textFieldValueFlow
                .debounce(500)
                .onEach { newValue ->
                    if (newValue.text == searchTextFieldValue.text) {
                        scope.launch(Dispatchers.IO) {
                            search(newValue.text)
                        }
                    }
                }
                .launchIn(scope)
        }

        if (isLoading) HorizontalProgressLoading()

        ListDetail(
            modifier = Modifier.padding(horizontal = 16.dp),
            isDetailOpen = isDetailOpen,
            setIsDetailOpen = { isDetailOpen = it },
            showListAndDetail = when (widthSizeClass) {
                WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> false
                WindowWidthSizeClass.Expanded -> true
                else -> true
            },
            twoPaneStrategy = HorizontalTwoPaneStrategy(
                splitFraction = 1f / 3f,
            ),
            detailKey = selectedItemIndex,
            displayFeatures = displayFeatures,
            list = { isDetailVisible ->
                val currentSelectedWordIndex = selectedItemIndex
                Column(Modifier.fillMaxSize()) {
                    SearchTextField(
                        searchTextFieldValue = searchTextFieldValue,
                        onSearchTextFieldValueChanged = {
                            viewModel.updateSearchTextFieldValue(it)
                        },
                        textFieldValueFlow = textFieldValueFlow,
                    )
                    ProgramListPane(
                        list = programList,
                        selectionState = if (isDetailVisible && currentSelectedWordIndex != null) {
                            SelectionVisibilityState.ShowSelection(currentSelectedWordIndex)
                        } else {
                            SelectionVisibilityState.NoSelection
                        },
                        onIndexClick = { index ->
                            selectedItemIndex = index
                            // Consider the detail to now be open. This acts like a navigation if
                            // there isn't room for both list and detail, and also will result
                            // in the detail remaining open in the case of resize.
                            isDetailOpen = true
                        },
                        modifier = if (isDetailVisible) {
                            Modifier.padding(end = 8.dp)
                        } else {
                            Modifier
                        },
                        widthSizeClass = widthSizeClass,
                    )
                }
            },
            detail = { isListVisible ->
                val backPressed = viewModel.onBackPress.collectAsState()
                BackHandler(backPressed.value) {
                    viewModel.onBackPress(false)
                }

                println("selectedWordIndex $selectedItemIndex")

                if (!isDetailApiCalled) {

                    val selectedMovie: Program? =
                        if (isDetailApiCalledIndex == null && selectedItemIndex != null) {
                            isDetailApiCalledIndex = selectedItemIndex
                            // first time render
                            programList[selectedItemIndex!!]

                        } else {
                            // second time render
                            // different one
                            if (isDetailApiCalledIndex != selectedItemIndex && selectedItemIndex != null) {
                                isDetailApiCalledIndex = selectedItemIndex
                                programList[selectedItemIndex!!]
                            } else {
                                null
                            }
                        }


                    if (selectedMovie != null) {
                        isDetailApiCalled = true
                        getMovieDetails(selectedMovie)
                    }

                }
//                if (movieDetailsInfo == MovieDetailsState.noCalled) {
//                    // on Loading State
//
//                    Box(Modifier.fillMaxSize(), Alignment.Center) {
//                        CircularProgressIndicator()
//                    }
//
//                } else {
//                    // reset Api Loading
//                    isDetailApiCalled = false
//                    DetailContent(
//                        movieDetailsInfo = movieDetailsInfo,
//                        modifier = if (isListVisible) {
//                            Modifier.padding(start = 8.dp)
//                        } else {
//                            Modifier
//                        },
//                        selectedWordIndex
//                    )
//                }

                when (val state = programDetailedState) {
                    Loading -> {
                        Box(Modifier.fillMaxSize(), Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is ProgramDetailedState.Success -> {
                        isDetailApiCalled = false
                        ProgramDetailPane(
                            program = state.program,
                            modifier = if (isListVisible) {
                                Modifier.padding(start = 8.dp)
                            } else {
                                Modifier
                            },
                        )
                    }
                }
            },
        )
    }
}

