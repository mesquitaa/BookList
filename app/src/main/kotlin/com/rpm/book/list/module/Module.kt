package com.rpm.book.list.module

import com.rpm.book.list.repository.BookRepository
import com.rpm.book.list.repository.remote.BookListRemoteRepository
import com.rpm.book.list.service.BookListService
import com.rpm.book.list.usecases.BookListUseCase
import com.rpm.book.list.usecases.impl.BookListUseCaseImp
import com.rpm.book.list.viewmodel.BookListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://private-anon-dab884d706-tpbookserver.apiary-mock.com/"

val bookListModule =
  module {
    single<Retrofit> {
      val loggingInterceptor = HttpLoggingInterceptor()
      if (BuildConfig.DEBUG) {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      } else {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
      }

      val okHttpClient =
        OkHttpClient
          .Builder()
          .addInterceptor(loggingInterceptor)
          .build()

      Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    }
    single { get<Retrofit>().create(BookListService::class.java) }
    single<BookListUseCase> { BookListUseCaseImp(get()) }
    single<BookRepository> { BookListRemoteRepository(get()) }
    viewModel { BookListViewModel(get()) }
  }
