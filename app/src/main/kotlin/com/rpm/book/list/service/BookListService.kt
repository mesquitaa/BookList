package com.rpm.book.list.service

import com.rpm.book.list.models.Book
import retrofit2.http.GET

interface BookListService {
  @GET("books")
  suspend fun getBooks(): List<Book>
}
