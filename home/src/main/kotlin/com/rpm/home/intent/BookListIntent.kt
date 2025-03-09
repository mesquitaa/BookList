package com.rpm.home.intent

sealed class BookListIntent {
  object LoadBookList : BookListIntent()
}
