package com.rpm.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rpm.home.intent.BookListIntent
import com.rpm.home.models.Book
import com.rpm.home.state.BookListState
import com.rpm.home.viewmodel.BookListViewModel
import com.rpm.home.extensions.formatAsCurrency
import com.rpm.home.ui.theme.BookListTheme

@Composable
fun BookListScreen(
  viewModel: BookListViewModel,
  onBookClick: (Int) -> Unit,
) {
  BookListTheme {
    LaunchedEffect(Unit) {
      viewModel.processIntent(BookListIntent.LoadBookList)
    }

    val state = viewModel.state.value
    BookListContent(state, onBookClick)
  }
}

@Composable
fun BookListContent(
  state: BookListState,
  onBookClick: (Int) -> Unit,
) {
  when (state) {
    is BookListState.Idle -> {}
    is BookListState.Loading -> LoadingScreen()
    is BookListState.Success -> BookList(books = state.books, onBookClick = onBookClick)
    is BookListState.Error -> ErrorScreen(message = state.message)
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
fun BookList(
  books: List<Book>,
  onBookClick: (Int) -> Unit,
) {
  LazyColumn {
    items(books) { book ->
      BookItem(book = book, onClick = { onBookClick(book.id) })
    }
  }
}

@Composable
fun BookItem(
  book: Book,
  onClick: () -> Unit,
) {
  Card(
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick() },
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
      Text(
        text = book.price.formatAsCurrency(book.currencyCode),
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
  val book =
    Book(
      id = 1,
      title = "Title",
      author = "Uncle Bob",
      isbn = "123456789",
      price = 1000,
      currencyCode = "EUR",
    )
  BookItem(book = book, onClick = {})
}
