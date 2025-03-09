package com.rpm.details.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpm.details.intent.BookDetailsIntent
import com.rpm.details.state.BookDetailsState
import com.rpm.details.usecases.BookDetailsUseCase
import kotlinx.coroutines.launch

class BookDetailViewModel(
  private val bookDetailsUseCase: BookDetailsUseCase,
) : ViewModel() {
  private val _state = mutableStateOf<BookDetailsState>(BookDetailsState.Idle)
  val state: State<BookDetailsState> = _state

  fun processIntent(intent: BookDetailsIntent) {
    when (intent) {
      is BookDetailsIntent.LoadBookDetails -> loadBookDetail(intent.id)
    }
  }

  private fun loadBookDetail(id: String) = viewModelScope.launch {
    _state.value = BookDetailsState.Loading
    _state.value = bookDetailsUseCase.invoke(id)
  }
}
