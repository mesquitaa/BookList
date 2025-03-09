package com.rpm.book.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rpm.book.list.view.BookListScreen
import com.rpm.book.list.viewmodel.BookListViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.appNavGraph(
  navController: NavHostController,
  bookListViewModel: BookListViewModel,
) {
  composable(AppScreen.BookList.route) {
    BookListScreen(viewModel = bookListViewModel) { bookId ->
      navController.navigate(AppScreen.BookDetail.createRoute(bookId))
    }
  }
  composable(
    route = AppScreen.BookDetail.route,
    arguments = AppScreen.BookDetail.arguments,
  ) { backStackEntry ->
    val bookId = backStackEntry.arguments?.getInt("id")
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("Book Detail") },
          navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
          },
        )
      },
    ) { innerPadding ->
      Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Book id: $bookId", modifier = Modifier.padding(innerPadding))
      }
    }
  }
}
