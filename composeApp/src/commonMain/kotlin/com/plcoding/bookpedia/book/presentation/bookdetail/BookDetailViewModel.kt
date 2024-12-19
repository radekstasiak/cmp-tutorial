package com.plcoding.bookpedia.book.presentation.bookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    private val savedStateHandler: SavedStateHandle
) : ViewModel() {

    private val bookId = savedStateHandler.toRoute<Route.BookDetails>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()
        .onStart {
            fetchBookDescription()
            observerFavoriteStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnSelectedBookChange -> {
                _state.update { it.copy(book = action.book) }
            }

            BookDetailAction.OnBackClicked -> {

            }

            BookDetailAction.OnFavoriteClick -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        bookRepository.deleteFromInstance(bookId)
                    } else {
                        state.value.book?.let { book ->
                            bookRepository.markAsFavorite(book)
                        }

                    }
                }
            }

        }
    }

    private fun observerFavoriteStatus() {
        bookRepository
            .isBookFavorite(id = bookId)
            .onEach { isFavorite ->
                _state.update {
                    it.copy(
                        isFavorite = isFavorite
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository
                .getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update {
                        it.copy(
                            book = it.book?.copy(
                                description = description
                            ),
                            isLoading = false
                        )
                    }
                }
        }
    }
}
