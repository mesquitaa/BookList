package com.rpm.home.repository.remote

import com.rpm.home.models.Book
import com.rpm.home.repository.BookRepository
import com.rpm.home.service.BookListService

class BookListRemoteRepository(
  private val service: BookListService,
) : BookRepository {
  override suspend fun getBooks(): List<Book> = service.getBooks()
}
