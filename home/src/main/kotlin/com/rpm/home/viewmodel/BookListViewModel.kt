package com.rpm.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpm.home.intent.BookListIntent
import com.rpm.home.state.BookListState
import com.rpm.home.usecases.BookListUseCase
import kotlinx.coroutines.launch

class BookListViewModel(
  private val listBookListUseCase: BookListUseCase,
) : ViewModel() {
  private val _state = mutableStateOf<BookListState>(BookListState.Idle)
  val state: State<BookListState> = _state

  fun processIntent(intent: BookListIntent) {
    when (intent) {
      is BookListIntent.LoadBookList -> loadBookList()
    }
  }

  private fun loadBookList() =
    viewModelScope.launch {
      _state.value = BookListState.Loading
      _state.value = listBookListUseCase.invoke()
    }
}
