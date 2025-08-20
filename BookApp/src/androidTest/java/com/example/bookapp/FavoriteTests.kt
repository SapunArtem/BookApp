package com.example.bookapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.bookapp.presentation.BookApp
import org.junit.Rule
import org.junit.Test


class FavoriteTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<BookApp>()

    @Test
    fun addAndRemoveBookFromFavorites() {

        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("BookList").fetchSemanticsNodes().isNotEmpty()
        }


        // Ищем книгу
        // Вводим поисковый запрос
        composeTestRule.onNodeWithTag("BookSearch").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BooksSearchInputField").performTextClearance()
        composeTestRule.onNodeWithTag("BooksSearchInputField").performTextInput("android")
        composeTestRule.onNodeWithTag("BooksSearchInputField").performImeAction()

        // Ждем загрузки
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("BookList").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onAllNodes(hasTestTag("BookCard"))[0].performClick()

        // Переходим к деталям
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("BtnAddFavorite").fetchSemanticsNodes().isNotEmpty()
        }
        // Добавляем в избранное
        composeTestRule.onNodeWithTag("BtnAddFavorite").performClick()

        // Возвращаемся назад
        composeTestRule.onNodeWithTag("backBtn").performClick()

        // Переходим в избранное
        composeTestRule.onNodeWithTag(R.string.favorite.toString()).performClick()

        composeTestRule.onNodeWithTag("FavoriteList").assertIsDisplayed()
        // Проверяем, что книга в избранном
        composeTestRule.onAllNodes(hasTestTag("BookCard"))[0].assertIsDisplayed()


        // Удаляем из избранного
        composeTestRule.onAllNodes(hasTestTag("BookCard"))[0].performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("BtnAddFavorite").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("BtnAddFavorite").performClick()
        composeTestRule.onNodeWithTag("backBtn").performClick()

        // Проверяем empty state
        composeTestRule.onNodeWithTag("EmptyStateMessage").assertExists()
    }

}
