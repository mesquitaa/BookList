package com.rpm.home.repository.remote

import com.rpm.home.service.BookListService
import com.rpm.home.repository.remote.BookListRemoteRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class BookListRemoteRepositoryTest {
  private val mockService = mockk<BookListService>(relaxed = true)

  private val subject by lazy {
    BookListRemoteRepository(mockService)
  }

  @Test
  fun `getBooks - should call service`() =
    runBlocking {
      // EXECUTING
      subject.getBooks()

      // VERIFYING
      coVerify { mockService.getBooks() }
    }
}
