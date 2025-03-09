package com.rpm.home.service

import com.rpm.home.models.Book
import retrofit2.http.GET

interface BookListService {
  @GET("books")
  suspend fun getBooks(): List<Book>
}
