package utils

import com.example.bookapp.BuildConfig

class Constant {
    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
        val API_KEY = BuildConfig.BOOKS_API_KEY
    }
}