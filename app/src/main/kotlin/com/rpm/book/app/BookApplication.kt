package com.rpm.book.app

import android.app.Application
import com.rpm.book.list.module.bookListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BookApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger(level = Level.DEBUG)
      androidContext(this@BookApplication)
      modules(bookListModule)
    }
  }
}
