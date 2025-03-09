package com.rpm.book.list.state

import com.rpm.book.list.models.Book

sealed class BookListState {
  data object Idle : BookListState()

  data object Loading : BookListState()

  data class Success(
    val books: List<Book>,
  ) : BookListState()

  data class Error(
    val message: String,
  ) : BookListState()
}
