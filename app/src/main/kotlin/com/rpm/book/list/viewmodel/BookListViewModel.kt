package com.rpm.book.list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpm.book.list.intent.BookListIntent
import com.rpm.book.list.state.BookListState
import com.rpm.book.list.usecases.BookListUseCase
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
