package com.rpm.book.list.intent

sealed class BookListIntent {
  object LoadBookList : BookListIntent()
}
