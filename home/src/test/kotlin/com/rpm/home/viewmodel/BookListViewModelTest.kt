package com.rpm.home.viewmodel

import com.rpm.home.intent.BookListIntent
import com.rpm.home.state.BookListState
import com.rpm.home.usecases.BookListUseCase
import com.rpm.home.viewmodel.BookListViewModel
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
