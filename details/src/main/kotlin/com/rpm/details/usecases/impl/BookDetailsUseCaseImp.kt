package com.rpm.details.usecases.impl

import com.rpm.details.repository.BookDetailRepository
import com.rpm.details.state.BookDetailsState
import com.rpm.details.usecases.BookDetailsUseCase

class BookDetailsUseCaseImp(
  private val bookDetailRepository: BookDetailRepository,
) : BookDetailsUseCase {
  override suspend fun invoke(bookId: String): BookDetailsState =
    try {
      val book = bookDetailRepository.getBookDetail(bookId)
      BookDetailsState.Success(book)
    } catch (e: Exception) {
      BookDetailsState.Error(e.message ?: "An error occurred")
    }
}
