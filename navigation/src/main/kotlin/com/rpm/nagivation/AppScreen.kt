package com.rpm.nagivation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class AppScreen(
  val route: String,
  val arguments: List<NamedNavArgument> = emptyList(),
) {
  data object BookList : AppScreen("list")

  data object BookDetail : AppScreen(
    route = "detail/{id}",
    arguments = listOf(navArgument("id") { type = NavType.IntType }),
  ) {
    fun createRoute(id: Int): String = "detail/$id"
  }
}
