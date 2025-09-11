package com.rpm.details.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rpm.details.intent.BookDetailsIntent
import com.rpm.details.models.BookDetail
import com.rpm.details.state.BookDetailsState
import com.rpm.details.theme.BookDetailsTheme
import com.rpm.details.viewmodel.BookDetailViewModel

@Composable
fun BookDetailScreen(
  viewModel: BookDetailViewModel,
  onBackPress: () -> Unit,
  bookId: Int,
) {
  BookDetailsTheme(onBackPress = onBackPress) {
    LaunchedEffect(Unit) {
      viewModel.processIntent(BookDetailsIntent.LoadBookDetails(bookId.toString()))
    }

    val state = viewModel.state.collectAsState()
    BookDetailContent(state)
  }
}

@Composable
fun BookDetailContent(
  state: State<BookDetailsState>,
) {
  when (val st = state.value) {
    is BookDetailsState.Idle -> {}
    is BookDetailsState.Loading -> LoadingScreen()
    is BookDetailsState.Success -> BookItem(book = st.book)
    is BookDetailsState.Error -> ErrorScreen(message = st.message)
  }
}

@Composable
fun LoadingScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
  }
}

@Composable
fun ErrorScreen(message: String) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(
      text = message,
      style = MaterialTheme.typography.bodyLarge,
    )
  }
}

@Composable
fun BookItem(
  book: BookDetail,
) {
  Column(
    modifier = Modifier.padding(8.dp),
  ) {
    Text(
      text = book.title,
      style =
        MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
        ),
    )
    Text(
      text = book.author,
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
  val book =
    BookDetail(
      id = 1,
      title = "Title",
      author = "Uncle Bob",
      isbn = "123456789",
      price = 1000,
      currencyCode = "EUR",
    )
  BookItem(book = book)
}
