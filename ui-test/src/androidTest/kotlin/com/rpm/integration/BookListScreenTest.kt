package com.rpm.integration

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.rpm.home.view.BookListScreen
import com.rpm.home.viewmodel.BookListViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@RunWith(AndroidJUnit4ClassRunner::class)
class BookListScreenTest : KoinTest {

  @get:Rule val composeTestRule = createComposeRule()
  @get:Rule val koinTestRule = KoinTestRule.create()

  @Before
  fun setup() {
  }

  @After
  fun tearDown() {
  }

  @Test
  fun bookListScreen_displaysListOfBooks() {
    composeTestRule.setContent {
      val viewModel by inject<BookListViewModel>()

      BookListScreen(viewModel) {

      }
    }
    composeTestRule.onNodeWithText("").assertIsDisplayed()
  }
}
