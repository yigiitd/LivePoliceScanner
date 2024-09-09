package com.oakssoftware.livepolicescanner.presentation.stations.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun StationsSearchBar(
    hint: String = "",
    searchText: String,
    onSearch: (String) -> Unit = {},
    onCloseClicked: () -> Unit = {}
) {
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    TextField(
        modifier = Modifier.fillMaxWidth()
            .fillMaxWidth()
            .padding(top = 16.dp, start = 4.dp, end = 4.dp)
            .onFocusChanged {
                isHintDisplayed = it.isFocused != true && searchText.isEmpty()
            },
        value = searchText,
        onValueChange = {
            onSearch(it)
        },
        placeholder = {
            Text(
                text = "Search here...",
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = {
                    onCloseClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(searchText)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                onSearch(searchText)
            }
        )
    )
}