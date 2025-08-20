package com.example.bookapp.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.random.Random

/**
 * Interceptor для повторных попыток выполнения сетевого запроса.
 * Используется при временных ошибках соединения.
 *
 * @param maxRetries максимальное количество попыток.
 * @param delayMillis задержка между попытками (увеличивается случайным образом).
 */
class RetryInterceptor(
    private val maxRetries: Int = 3,
    private val delayMillis: Long = 1000L
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var counter = 0
        var response: Response

        while (true) {
            counter++
            try {
                response = chain.proceed(chain.request())
                if (response.isSuccessful) {
                    return response
                } else if (counter >= maxRetries) {
                    return response
                }
            } catch (e: IOException) {
                if (counter >= maxRetries) {
                    throw e
                }
            }
            val delay = (delayMillis * (1 + Random.nextDouble())).toLong()
            Thread.sleep(delay)
        }
    }
}