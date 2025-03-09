package com.rpm.details.repository

import com.rpm.details.models.BookDetail

interface BookDetailRepository {
  suspend fun getBookDetail(bookId: String): BookDetail
}
