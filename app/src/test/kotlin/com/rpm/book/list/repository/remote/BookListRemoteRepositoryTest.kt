package com.rpm.book.list.repository.remote

import com.rpm.book.list.service.BookListService
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
      subject.getBooks()

      coVerify { mockService.getBooks() }
    }
}
