package com.rpm.details.usecases

import com.rpm.details.state.BookDetailsState

interface BookDetailsUseCase {
  suspend fun invoke(bookId: String): BookDetailsState
}
