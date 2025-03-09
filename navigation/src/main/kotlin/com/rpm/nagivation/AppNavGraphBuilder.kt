package com.rpm.nagivation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rpm.details.view.BookDetailScreen
import com.rpm.details.viewmodel.BookDetailViewModel
import com.rpm.home.view.BookListScreen
import com.rpm.home.viewmodel.BookListViewModel

fun NavGraphBuilder.appNavGraph(
  navController: NavHostController,
  bookListViewModel: BookListViewModel,
  bookDetail: BookDetailViewModel,
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
    val bookId = backStackEntry.arguments?.getInt("id") ?: 0
    BookDetailScreen(
      viewModel = bookDetail,
      bookId = bookId,
      onBackPress = navController::popBackStack,
    )
  }
}
