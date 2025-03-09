package com.rpm.book.list.usecases

import com.rpm.book.list.state.BookListState

interface BookListUseCase {
  suspend fun invoke(): BookListState
}
