package com.rpm.book.list.viewmodel

import com.rpm.book.list.intent.BookListIntent
import com.rpm.book.list.state.BookListState
import com.rpm.book.list.usecases.BookListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookListViewModelTest {
  private val mockUseCase = mockk<BookListUseCase>(relaxed = true)

  private val subject by lazy { BookListViewModel(mockUseCase) }

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
      val mockListState = mockk<BookListState.Success>(relaxed = true)
      coEvery { mockUseCase.invoke() } returns mockListState

      // EXECUTING
      subject.processIntent(BookListIntent.LoadBookList)

      // VERIFYING
      assertEquals(mockListState, subject.state.value)
    }
}
