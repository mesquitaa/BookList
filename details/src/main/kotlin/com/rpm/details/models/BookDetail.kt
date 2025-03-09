package com.rpm.details.models

data class BookDetail(
  val id: Int,
  val title: String,
  val author: String,
  val isbn: String,
  val price: Int,
  val currencyCode: String,
)
