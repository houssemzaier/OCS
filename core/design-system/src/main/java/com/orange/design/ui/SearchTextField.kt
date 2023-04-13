package com.orange.design.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SearchTextField(
    searchTextFieldValue: TextFieldValue,
    onSearchTextFieldValueChanged: (newValue: TextFieldValue) -> Unit,
    textFieldValueFlow: MutableStateFlow<TextFieldValue>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        Alignment.Center,
    ) {
        OutlinedTextField(
            value = searchTextFieldValue,
            onValueChange = { newValue: TextFieldValue ->
                onSearchTextFieldValueChanged(newValue)
                textFieldValueFlow.tryEmit(newValue)
            },
            label = {
                Text(
                    text = "Recherche d'un program",
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                )
            },
            modifier = Modifier
                .height(64.dp)
                .widthIn(300.dp, 400.dp),
            shape = MaterialTheme.shapes.medium.copy(CornerSize(30.dp)),
            placeholder = {
                Text(
                    text = "Eg : rouge",
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,

                ),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
            ),
            singleLine = true,
            trailingIcon = {
                if (searchTextFieldValue.text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onSearchTextFieldValueChanged(TextFieldValue(""))
                            textFieldValueFlow.tryEmit(TextFieldValue(""))
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                        )
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun SearchTextField_Prev() {
    SearchTextField(
        searchTextFieldValue = TextFieldValue(""),
        onSearchTextFieldValueChanged = {},
        textFieldValueFlow = MutableStateFlow(TextFieldValue("Vous")),
    )
}
