package com.rpm.book.list.repository

import com.rpm.book.list.models.Book

interface BookRepository {
  suspend fun getBooks(): List<Book>
}
