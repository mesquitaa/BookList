package com.rpm.booklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rpm.booklist.list.intent.BookListIntent
import com.rpm.booklist.list.view.BookListScreen
import com.rpm.booklist.list.viewmodel.BookListViewModel
import com.rpm.booklist.ui.theme.BookListTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      val navController = rememberNavController()
      val bookListViewModel: BookListViewModel by inject()
//      val bookDetailViewModel: BookDetailViewModel = hiltViewModel()

      NavHost(
        navController = navController,
        startDestination = "list"
      ) {
        composable("list") {
          BookListTheme {
            BookListScreen(viewModel = bookListViewModel) {
              navController.navigate("detail/${it}")
            }
          }
        }
        composable("detail/{id}") {
          BookListTheme {
            Text("Android")
          }
        }
//        appNavGraph(
//          navController = navController,
//          bookListViewModel = bookListViewModel,
//          bookDetailViewModel = bookDetailViewModel
//        )
      }

//      BookListTheme {
//        Text("Android")
//      }
    }
  }
}
