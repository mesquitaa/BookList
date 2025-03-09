package com.rpm.details.module

import com.rpm.details.repository.BookDetailRepository
import com.rpm.details.repository.remote.BookDetailRemoteRepository
import com.rpm.details.service.BookDetailService
import com.rpm.details.usecases.BookDetailsUseCase
import com.rpm.details.usecases.impl.BookDetailsUseCaseImp
import com.rpm.details.viewmodel.BookDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://private-anon-dab884d706-tpbookserver.apiary-mock.com/"

val bookDetailModule =
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
    single { get<Retrofit>().create(BookDetailService::class.java) }
    single<BookDetailsUseCase> { BookDetailsUseCaseImp(get()) }
    single<BookDetailRepository> { BookDetailRemoteRepository(get()) }
    viewModel { BookDetailViewModel(get()) }
  }
