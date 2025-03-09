package com.rpm.details.service

import com.rpm.details.models.BookDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailService {
  @GET("book/{id}")
  suspend fun getBookDetails(@Path("id") id: String): BookDetail
}
