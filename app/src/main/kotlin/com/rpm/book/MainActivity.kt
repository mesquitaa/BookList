package com.rpm.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rpm.home.viewmodel.BookListViewModel
import com.rpm.nagivation.AppScreen
import com.rpm.nagivation.appNavGraph
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val navController = rememberNavController()
      val bookListViewModel: BookListViewModel by inject()

      NavHost(
        navController = navController,
        startDestination = AppScreen.BookList.route,
      ) {
        appNavGraph(navController, bookListViewModel)
      }
    }
  }
}
