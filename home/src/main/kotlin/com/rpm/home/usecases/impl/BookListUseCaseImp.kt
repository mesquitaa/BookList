package com.rpm.home.usecases.impl

import com.rpm.home.repository.BookRepository
import com.rpm.home.state.BookListState
import com.rpm.home.usecases.BookListUseCase

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
