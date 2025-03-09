package com.rpm.details.viewmodel

import com.rpm.details.intent.BookDetailsIntent
import com.rpm.details.state.BookDetailsState
import com.rpm.details.usecases.BookDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailViewModelTest {
  companion object {
    private const val BOOK_ID = "1"
  }

  private val mockUseCase = mockk<BookDetailsUseCase>(relaxed = true)

  private val subject by lazy { BookDetailViewModel(mockUseCase) }

  @BeforeEach
  fun setUp() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @AfterEach
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `processIntent - WHEN method is called THEN should invoke use case`() =
    runBlocking {
      // STUBBING
      val mockListState = mockk<BookDetailsState.Success>(relaxed = true)
      coEvery { mockUseCase.invoke(BOOK_ID) } returns mockListState

      // EXECUTING
      subject.processIntent(BookDetailsIntent.LoadBookDetails(BOOK_ID))

      // VERIFYING
      assertEquals(mockListState, subject.state.value)
    }
}
