package com.rpm.book.list.usecases.impl

import com.rpm.book.list.repository.BookRepository
import com.rpm.book.list.state.BookListState
import com.rpm.book.list.usecases.BookListUseCase

class BookListUseCaseImp(
  private val bookRepository: BookRepository,
) : BookListUseCase {
  override suspend fun invoke(): BookListState =
    try {
      val books = bookRepository.getBooks()
      BookListState.Success(books)
    } catch (e: Exception) {
      BookListState.Error(e.message ?: "An error occurred")
    }
}
