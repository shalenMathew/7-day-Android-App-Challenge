package com.mani.quotify007.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.screens.quote.QuotesScreen

@Composable
fun SearchScreen(
    quotes: List<Quote>,
    onEvent: (MainEvent) -> Unit,
    onCopyText: (Quote) -> Unit,
    onShareClick: (Quote) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredQuotes = quotes.filter { it.text.contains(searchQuery.text, ignoreCase = true) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery,
            label = { Text("Search") },
            onValueChange = { searchQuery = it },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(filteredQuotes) { quote ->
                QuotesScreen(
                    quote,
                    onEvent = { onEvent(MainEvent.AddFavorite(quote)) },
                    true,
                    onCopyText = onCopyText,
                    onShareClick = onShareClick
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        listOf(Quote(0, "Sample quote")),
        onEvent = {},
        onCopyText = {},
        onShareClick = {}
    )
}