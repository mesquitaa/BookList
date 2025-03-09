package com.rpm.book.extensions

import java.util.Currency
import java.util.Locale

fun Int.formatAsCurrency(
  currencyCode: String,
  locale: Locale = Locale.getDefault(),
): String {
  val amount = this / 100.0
  val symbol = currencyCode.getCurrencySymbol(locale)
  return "%s%.2f".format(symbol, amount)
}

private fun String.getCurrencySymbol(locale: Locale = Locale.getDefault()): String =
  try {
    Currency.getInstance(this).getSymbol(locale)
  } catch (e: IllegalArgumentException) {
    this // Returning the proper code if it is invalid (ex: "XYZ" → "XYZ12.99")
  } catch (e: NullPointerException) {
    this
  }
