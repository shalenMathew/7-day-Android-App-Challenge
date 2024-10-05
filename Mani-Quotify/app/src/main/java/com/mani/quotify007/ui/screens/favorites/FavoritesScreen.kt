package com.mani.quotify007.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.screens.quote.QuotesScreen

@Composable
fun FavoritesScreen(
    quotes: List<Quote>,
    onEvent: (MainEvent) -> Unit,
    onCopyText: (Quote) -> Unit,
    onShareClick: (Quote) -> Unit
) {
    if (quotes.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No favorite quotes available.")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(quotes) { quote ->
                    QuotesScreen(
                        quote,
                        onEvent = { onEvent(MainEvent.RemoveFavorite(quote)) },
                        false,
                        onCopyText = onCopyText,
                        onShareClick = onShareClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(
        listOf(Quote("Sample quote", "Sample Author")),
        onEvent = {},
        onCopyText = {},
        onShareClick = {})
}