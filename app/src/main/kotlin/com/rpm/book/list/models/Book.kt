package com.rpm.book.list.models

data class Book(
  val id: Int,
  val title: String,
  val author: String,
  val isbn: String,
  val price: Int,
  val currencyCode: String,
)
