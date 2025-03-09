package com.rpm.details.repository.remote

import com.rpm.details.models.BookDetail
import com.rpm.details.repository.BookDetailRepository
import com.rpm.details.service.BookDetailService

class BookDetailRemoteRepository(
  private val service: BookDetailService,
) : BookDetailRepository {
  override suspend fun getBookDetail(bookId: String): BookDetail = service.getBookDetails(bookId)
}
