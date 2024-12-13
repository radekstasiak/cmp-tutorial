package com.plcoding.bookpedia.book.data.mapper

import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book =
    Book(
        id = this.id.substringAfterLast("/"),
        title = this.title,
        imageUrl = if (this.coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/olid/${coverAlternativeKey}-L.jpg"
        },
        authors = this.authorNames.orEmpty(),
        description = null,
        languages = this.languages.orEmpty(),
        firstPublishYear = this.firstPublishYear.toString(),
        averageRating = this.ratingsAverage,
        ratingCount = this.ratingsCount ?: 0,
        numPages = this.numPagesMedian,
        numEditions = this.numEditions ?: 0
    )
