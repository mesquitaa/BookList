package com.rpm.details.usecases.impl

import com.rpm.details.models.BookDetail
import com.rpm.details.repository.BookDetailRepository
import com.rpm.details.state.BookDetailsState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BookDetailsUseCaseImpTest {

  companion object {
    private const val BOOK_ID = "1"
  }

  private val mockRepository = mockk<BookDetailRepository>(relaxed = true)

  private val subject by lazy { BookDetailsUseCaseImp(mockRepository) }

  @Test
  fun `invoke - WHEN repository returns success THEN should return list of books`() =
    runBlocking {
      // STUBBING
      val mockBook = mockk<BookDetail>()

      val expected = BookDetailsState.Success(mockBook)
      coEvery { mockRepository.getBookDetail(BOOK_ID) } returns mockBook

      // EXECUTING
      val result = subject.invoke(BOOK_ID)

      // VERIFYING
      assertEquals(result, expected)
    }

  @Test
  fun `invoke - WHEN repository returns error with no message THEN should return common error`() =
    runBlocking {
      // STUBBING
      coEvery { mockRepository.getBookDetail(BOOK_ID) } throws Exception()

      // EXECUTING
      val result = subject.invoke(BOOK_ID)

      // VERIFYING
      assertTrue(result is BookDetailsState.Error)
      assertEquals("An error occurred", (result as BookDetailsState.Error).message)
    }

  @Test
  fun `invoke - WHEN repository returns error with message THEN should return message error`() =
    runBlocking {
      // STUBBING
      coEvery { mockRepository.getBookDetail(BOOK_ID) } throws Exception("server error")

      // EXECUTING
      val result = subject.invoke(BOOK_ID)

      // VERIFYING
      assertTrue(result is BookDetailsState.Error)
      assertEquals("server error", (result as BookDetailsState.Error).message)
    }
}
