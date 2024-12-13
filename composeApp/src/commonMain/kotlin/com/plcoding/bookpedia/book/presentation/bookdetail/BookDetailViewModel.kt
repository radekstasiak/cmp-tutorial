package com.plcoding.bookpedia.book.presentation.bookdetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailViewModel() : ViewModel() {

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnSelectedBookChange -> {
                _state.update { it.copy(book = action.book) }
            }

            BookDetailAction.OnBackClicked -> {

            }

            BookDetailAction.OnFavoriteClick -> {

            }

        }
    }
}
