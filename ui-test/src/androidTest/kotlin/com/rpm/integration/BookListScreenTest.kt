package com.rpm.integration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rpm.home.view.BookItemPreview
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookListScreenTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Before
  fun setup() {
  }

  @After
  fun tearDown() {
  }

  @Test
  fun bookItemPreview_isDisplayed() {
    composeTestRule.setContent {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.White)
      ) {
        BookItemPreview()
      }
    }

    Thread.sleep(3000)
    composeTestRule.onNodeWithText("Title").assertIsDisplayed()
  }
}
