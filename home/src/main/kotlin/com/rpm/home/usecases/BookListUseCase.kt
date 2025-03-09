package com.rpm.home.usecases

import com.rpm.home.state.BookListState

interface BookListUseCase {
  suspend fun invoke(): BookListState
}
