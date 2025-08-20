package com.example.bookapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Правило для замены главного [Dispatchers.Main] на тестовый диспетчер во время выполнения тестов.
 *
 * Это позволяет управлять корутинами через [TestDispatcher] и синхронно выполнять suspend-функции.
 */
class MainCoroutineRule : TestWatcher() {
    lateinit var testDispatcher: TestDispatcher

    override fun starting(description: Description) {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}