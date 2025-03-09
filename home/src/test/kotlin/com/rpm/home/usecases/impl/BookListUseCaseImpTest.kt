package com.rpm.home.usecases.impl

import com.rpm.home.models.Book
import com.rpm.home.repository.BookRepository
import com.rpm.home.state.BookListState
import com.rpm.home.usecases.impl.BookListUseCaseImp
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BookListUseCaseImpTest {
  private val mockRepository = mockk<BookRepository>(relaxed = true)

  private val subject by lazy { BookListUseCaseImp(mockRepository) }

  @Test
  fun `invoke - WHEN repository returns success THEN should return list of books`() =
    runBlocking {
      // STUBBING
      val mockBook = mockk<Book>()
      val sutList = listOf(mockBook)

      val expected = BookListState.Success(listOf(mockBook))
      coEvery { mockRepository.getBooks() } returns sutList

      // EXECUTING
      val result = subject.invoke()

      // VERIFYING
      assertEquals(result, expected)
    }

  @Test
  fun `invoke - WHEN repository returns error with no message THEN should return common error`() =
    runBlocking {
      // STUBBING
      coEvery { mockRepository.getBooks() } throws Exception()

      // EXECUTING
      val result = subject.invoke()

      // VERIFYING
      assertTrue(result is BookListState.Error)
      assertEquals("An error occurred", (result as BookListState.Error).message)
    }

  @Test
  fun `invoke - WHEN repository returns error with message THEN should return message error`() =
    runBlocking {
      // STUBBING
      coEvery { mockRepository.getBooks() } throws Exception("server error")

      // EXECUTING
      val result = subject.invoke()

      // VERIFYING
      assertTrue(result is BookListState.Error)
      assertEquals("server error", (result as BookListState.Error).message)
    }
}
