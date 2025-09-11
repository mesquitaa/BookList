package com.rpm.details.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpm.details.intent.BookDetailsIntent
import com.rpm.details.state.BookDetailsState
import com.rpm.details.usecases.BookDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookDetailViewModel(
  private val bookDetailsUseCase: BookDetailsUseCase,
) : ViewModel() {
  private val _uiState = MutableStateFlow<BookDetailsState>(BookDetailsState.Idle)
  val state: StateFlow<BookDetailsState> = _uiState

  fun processIntent(intent: BookDetailsIntent) {
    when (intent) {
      is BookDetailsIntent.LoadBookDetails -> loadBookDetail(intent.id)
    }
  }

  private fun loadBookDetail(id: String) = viewModelScope.launch {
    _uiState.value = BookDetailsState.Loading
    _uiState.value = bookDetailsUseCase.invoke(id)
  }
}
