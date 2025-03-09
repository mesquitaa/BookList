package com.rpm.details.intent

sealed class BookDetailsIntent {
  data class LoadBookDetails(val id: String) : BookDetailsIntent()
}
