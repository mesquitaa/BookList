package com.rpm.details.repository.remote

import com.rpm.details.service.BookDetailService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BookDetailRemoteRepositoryTest {
  private val mockService = mockk<BookDetailService>(relaxed = true)

  private val subject by lazy {
    BookDetailRemoteRepository(mockService)
  }

  @Test
  fun `getBooks - should call service`() = runBlocking {
    // STUBBING
    val sutBookId = "1"

    // EXECUTING
    subject.getBookDetail(sutBookId)

    // VERIFYING
    coVerify { mockService.getBookDetails(sutBookId) }
  }
}
