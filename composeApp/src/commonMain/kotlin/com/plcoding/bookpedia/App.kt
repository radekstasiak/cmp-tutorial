package com.plcoding.bookpedia

import androidx.compose.runtime.*

import com.plcoding.bookpedia.book.presentation.booklist.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = viewModel,
        onBookClick = {}
    )
}
