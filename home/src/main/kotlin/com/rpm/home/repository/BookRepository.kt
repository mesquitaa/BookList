package com.rpm.home.repository

import com.rpm.home.models.Book

interface BookRepository {
  suspend fun getBooks(): List<Book>
}
