package com.plcoding.bookpedia

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.booklist.components.BookListItem
import com.plcoding.bookpedia.book.presentation.booklist.components.BookSearchBar

@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BookSearchBar(
            searchQuery = "Kotlin",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BookListItemPreview() {
    BookListItem(
        book = Book(
            id = "1",
            title = "Book title",
            imageUrl = "",
            authors = listOf(),
            description = null,
            languages = listOf(),
            firstPublishYear = null,
            averageRating = 4.6,
            ratingCount = 3,
            numPages = 10,
            numEditions = 1
        ),
        onBookClick = {}
    )
}

