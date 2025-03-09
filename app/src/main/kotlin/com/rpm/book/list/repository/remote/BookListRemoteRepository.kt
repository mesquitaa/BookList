package com.rpm.book.list.repository.remote

import com.rpm.book.list.models.Book
import com.rpm.book.list.repository.BookRepository
import com.rpm.book.list.service.BookListService

class BookListRemoteRepository(
  private val service: BookListService,
) : BookRepository {
  override suspend fun getBooks(): List<Book> = service.getBooks()
}
