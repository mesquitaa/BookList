package com.rpm.details.state

import com.rpm.details.models.BookDetail

sealed class BookDetailsState {
  data object Idle : BookDetailsState()

  data object Loading : BookDetailsState()

  data class Success(
    val book: BookDetail,
  ) : BookDetailsState()

  data class Error(
    val message: String,
  ) : BookDetailsState()
}
