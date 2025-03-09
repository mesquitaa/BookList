package com.rpm.home.module

import com.rpm.home.repository.BookRepository
import com.rpm.home.repository.remote.BookListRemoteRepository
import com.rpm.home.service.BookListService
import com.rpm.home.usecases.BookListUseCase
import com.rpm.home.usecases.impl.BookListUseCaseImp
import com.rpm.home.viewmodel.BookListViewModel
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
