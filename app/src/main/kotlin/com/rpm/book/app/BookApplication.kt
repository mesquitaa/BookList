package com.rpm.booklist.app

import android.app.Application
import com.rpm.booklist.list.module.bookListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@BookApplication)
      modules(bookListModule)
    }
  }
}
