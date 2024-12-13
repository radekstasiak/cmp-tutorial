package com.plcoding.bookpedia.book.presentation.bookdetail

import com.plcoding.bookpedia.book.domain.Book

sealed interface BookDetailAction {

    data object OnBackClicked : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}
